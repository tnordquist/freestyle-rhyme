package edu.cnm.deepdive.freestylerhyme.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.freestylerhyme.BuildConfig;
import edu.cnm.deepdive.freestylerhyme.model.pojo.WordApiResponse;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The interface Word api service.
 */
public interface WordApiService {

  /**
   * Get single.
   *
   * @param word   the word
   * @param host   the host
   * @param apiKey the api key
   * @return the single
   */
  @GET("words/{word}/rhymes")
  Single<WordApiResponse> get(
      @Path("word") String word,
      @Header("x-rapidapi-host") String host,
      @Header("x-rapidapi-key") String apiKey);

  @GET("words/")
  Single<WordApiResponse> getRandom(
      @Query("random") boolean random,
      @Header("x-rapidapi-host") String host,
      @Header("x-rapidapi-key") String apiKey);

  /**
   * Gets instance.
   *
   * @return the instance
   */
  static WordApiService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * The type Instance holder.
   */
  class InstanceHolder {

    private static final WordApiService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WordApiService.class);
    }
  }

}
