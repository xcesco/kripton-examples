package com.abubusoft.filmfinder.service.api;

import android.support.annotation.Nullable;

import com.abubusoft.kripton.android.sqlite.commons.IOUtils;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.Body;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public final class ChunkingConverter {
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Chunked {
    }

    @Target(METHOD)
    @Retention(RUNTIME)
    @interface ChunkedResponse {
    }

    /**
     * A converter which removes known content lengths to force chunking when {@code @Chunked} is
     * present on {@code @Body} params.
     */
    static class ChunkingConverterFactory extends Converter.Factory {

        @Nullable
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            boolean isChunked = false;
            for (Annotation annotation : annotations) {
                isChunked |= annotation instanceof ChunkedResponse;
            }

            if (!isChunked) {
                return null;
            }

            // Look up the real converter to delegate to.
            final  Converter<ResponseBody, Object> delegate = retrofit.responseBodyConverter(type, annotations);

            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody value) throws IOException {
                    String read=IOUtils.readText(value.byteStream());
                    return read;
                }
            };
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            boolean isBody = false;
            boolean isChunked = false;
            for (Annotation annotation : parameterAnnotations) {
                isBody |= annotation instanceof Body;
                isChunked |= annotation instanceof Chunked;
            }
            if (!isBody || !isChunked) {
                return null;
            }

            // Look up the real converter to delegate to.
            final Converter<Object, RequestBody> delegate =
                    retrofit.nextRequestBodyConverter(this, type, parameterAnnotations, methodAnnotations);
            // Wrap it in a Converter which removes the content length from the delegate's body.
            return new Converter<Object, RequestBody>() {
                @Override
                public RequestBody convert(Object value) throws IOException {
                    final RequestBody realBody = delegate.convert(value);
                    return new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            return realBody.contentType();
                        }

                        @Override
                        public void writeTo(BufferedSink sink) throws IOException {
                            realBody.writeTo(sink);
                        }
                    };
                }
            };
        }
    }

}