package com.abubusoft.kripton.movie.kriptonmovie.persistence;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.movie.kriptonmovie.model.Director;
import com.abubusoft.kripton.movie.kriptonmovie.model.Movie;
import com.abubusoft.kripton.movie.kriptonmovie.model.MovieWithDirector;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.datasource.BindMovieDataSource;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;

    private MovieRepository() {
        dataSource = BindMovieDataSource.getInstance();
    }

    BindMovieDataSource dataSource;

    public void insertOrUpdateDirector(String directorFullNameExtra, String fullName) {
        dataSource.executeAsync(daoFactory -> {
            if (directorFullNameExtra != null) {
                // clicked on item row -> update
                Director directorToUpdate = daoFactory.getDirectorDao().findDirectorByName(directorFullNameExtra);
                if (directorToUpdate != null) {
                    if (!directorToUpdate.fullName.equals(fullName)) {
                        directorToUpdate.fullName = fullName;
                        daoFactory.getDirectorDao().update(directorToUpdate);
                    }
                }
            } else {
                // i don't need to create a directo to insert
                daoFactory.getDirectorDao().insert(fullName);
            }
            return TransactionResult.COMMIT;
        });
    }

    public LiveData<List<Director>> getAllDirectors() {
        return dataSource.getDirectorDao().getAllDirectors();
    }

    public void insertDirectors(List<Director> directors) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getDirectorDao().insert(directors);
            return TransactionResult.COMMIT;
        });
    }

    public void deleteMovies() {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getMovieDao().deleteAll();
            return TransactionResult.COMMIT;
        });
    }

    public LiveData<List<MovieWithDirector>> getAllMovies() {
        return dataSource.getMovieDao().getAllMovies();
    }

    public void updateMovie(Movie movie) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getMovieDao().update(movie);
            return TransactionResult.COMMIT;
        });
    }

    public void insertMovies(List<Movie> movies) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getMovieDao().insert(movies);
            return TransactionResult.COMMIT;
        });
    }

    public void deleteDirectors() {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getDirectorDao().deleteAll();
            return TransactionResult.COMMIT;
        });
    }
    
    public static MovieRepository getInstance() {
        if (instance==null) {
            instance=new MovieRepository();
        }
        
        return instance;
    }

    public void clearDb() {
        dataSource.clearDbAsync();
    }

    public void insertOrUpdateMovie(String movieTitle, String movieTitleExtra, String movieDirectorFullName) {
        BindMovieDataSource.getInstance().executeAsync(daoFactory -> {
            long directorId = -1;
            if (movieDirectorFullName != null) {
                // clicked on item row -> update
                Director directorToUpdate = daoFactory.getDirectorDao().findDirectorByName(movieDirectorFullName);
                if (directorToUpdate != null) {
                    directorId = directorToUpdate.id;

                    if (!directorToUpdate.fullName.equals(movieDirectorFullName)) {
                        directorToUpdate.fullName = movieDirectorFullName;
                        daoFactory.getDirectorDao().update(directorToUpdate);
                    }
                }
            } else {
                // we need director id for movie object; in case director is already in DB,
                // insert() would return -1, so we manually check if it exists and get
                // the id of already saved director
                Director newDirector = daoFactory.getDirectorDao().findDirectorByName(movieDirectorFullName);
                if (newDirector == null) {
                    directorId = (int) daoFactory.getDirectorDao().insert(movieDirectorFullName);
                } else {
                    directorId = newDirector.id;
                }
            }

            if (movieTitleExtra != null) {
                // clicked on item row -> update
                Movie movieToUpdate = daoFactory.getMovieDao().findMovieByTitle(movieTitleExtra);
                if (movieToUpdate != null) {
                    if (!movieToUpdate.title.equals(movieTitle)) {
                        movieToUpdate.title = movieTitle;
                        if (directorId != -1) {
                            movieToUpdate.directorId = directorId;
                        }
                        daoFactory.getMovieDao().update(movieToUpdate);
                    }
                }
            } else {
                // we can have many movies with same title but different director
                Movie newMovie = daoFactory.getMovieDao().findMovieByTitle(movieTitle);
                if (newMovie == null) {
                    daoFactory.getMovieDao().insert(movieTitle, directorId);
                } else {
                    if (newMovie.directorId != directorId) {
                        newMovie.directorId = directorId;
                        daoFactory.getMovieDao().update(newMovie);
                    }
                }
            }

            return TransactionResult.COMMIT;
        });
    }
}
