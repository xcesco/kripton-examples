package com.abubusoft.kripton.movie.kriptonmovie.persistence;

import com.abubusoft.kripton.movie.kriptonmovie.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface PersistenceComponent {
}
