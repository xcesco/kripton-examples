package com.abubusoft.filmfinder.service.api;

import android.content.Context;

import com.abubusoft.filmfinder.R;
import com.abubusoft.filmfinder.service.model.FilmDetail;
import com.abubusoft.filmfinder.service.model.Search;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.commons.IOUtils;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Retrofit;

public class FilmService {

    private final String apiKey;
    OmdbApi api;

    static final String BASE_URL = "http://www.omdbapi.com/";

    public FilmService(Context context) {
        apiKey = context.getResources().getString(R.string.omdb_api_key);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request req = chain.request();
                        Headers.Builder headersBuilder = req.headers().newBuilder();

                        Response res = chain.proceed(req.newBuilder().headers(headersBuilder.build()).build());

                        String val=IOUtils.readText(res.body().source().inputStream());

                        return res.newBuilder()
                                .header("Content-Encoding", "gzip")
                                .header("Content-Type", "application/json")
                                .build();
                    }
                }).
                        addInterceptor(new Interceptor() {

                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                RequestBody original = chain.request().body();
//                        Buffer buffer = new Buffer();
//                        original.writeTo(buffer);
//                        ByteString bytes = buffer.snapshot();
//                        RequestBody fixedLength = RequestBody.create(original.contentType(), bytes);
                                Request.Builder requestBuilder = chain.request().newBuilder();
//                        requestBuilder.method(chain.request().method(), fixedLength);
                                return chain.proceed(requestBuilder.build());
                            }
                        });
        // .addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new ChunkingConverter.ChunkingConverterFactory())
                /* .addConverterFactory(KriptonBinderConverterFactory
                         .create())*/
                .client(httpClient.build())
                .build();

        api = retrofit.create(OmdbApi.class);
    }

    public Search executeSearch(String search) {
        //Call<Search> call = api.search(search, this.apiKey);
        Call<ResponseBody> call = api.search(search, this.apiKey);

        try {
            byte[] result = call.execute().body().bytes();
            Buffer buffer = new Buffer();
            String value = IOUtils.readText(call.execute().body().byteStream());


            //RequestBody fixedLength = RequestBody.create(bytes, original.contentType());

            Logger.info("downloaded " + value.length());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Call<ResponseBody> executeGetById(String uid) {
        Call<ResponseBody> call = api.getFilm(uid, this.apiKey);
        return call;
    }
}
