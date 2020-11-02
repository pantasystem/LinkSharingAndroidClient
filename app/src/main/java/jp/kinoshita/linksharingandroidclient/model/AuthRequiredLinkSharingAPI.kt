package jp.kinoshita.linksharingandroidclient.model

import io.reactivex.Single
import jp.kinoshita.linksharingandroidapp.model.comments.CreateComment
import jp.kinoshita.linksharingandroidclient.model.comments.Comment
import jp.kinoshita.linksharingandroidclient.model.favorites.Favorite
import jp.kinoshita.linksharingandroidclient.model.notes.CreateNote
import jp.kinoshita.linksharingandroidclient.model.notes.IsFavorite
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import jp.kinoshita.linksharingandroidclient.model.users.User
import retrofit2.Response
import retrofit2.http.*

interface AuthRequiredLinkSharingAPI : LinkSharingAPI{

    @GET("me")
    fun me(): Single<User>

    @POST("notes")
    fun createNote(@Body createNote: CreateNote): Single<Response<Note>>

    @GET("notes")
    fun timeline(@Query("page") page: Int): Single<Response<Page<Note>>>

    @DELETE("notes/{noteId}")
    fun deleteNote(@Path("noteId") noteId: Long): Single<Response<Unit>>

    @POST("notes/{noteId}/favorites")
    fun favorite(@Path("noteId") noteId: Long): Single<Response<Favorite>>

    @DELETE("notes/{noteId}/favorites")
    fun unFavorite(@Path("noteId") noteId: Long): Single<Response<Favorite>>

    @GET("notes/{noteId}/my-favorite")
    fun hasFavorite(@Path("noteId") noteId: Long): Single<Response<IsFavorite>>

    @PUT("users/{userId}")
    fun follow(@Path("userId") userId: Long): Single<Response<Unit>>

    @DELETE("users/{userId}")
    fun unFollow(@Path("userId") userId: Long): Single<Response<Unit>>


    @POST("notes/{noteId}/comments")
    fun replyToNote(@Path("noteId") noteId: Long, @Body createComment: CreateComment): Single<Response<Comment>>


    @DELETE("notes/{noteId}/comments/{commentId}")
    fun deleteComment(@Path("noteId") noteId: Long, @Path("commentId") commentId: Long): Single<Response<Unit>>

    @GET("notifications")
    fun notifications(@Query("page") page: Int): Single<Response<Page<Notification>>>

    @PUT("notifications/{notificationId}")
    fun readNotification(@Path("notificationId") notificationId: Long): Single<Response<Notification>>

    @PUT("notifications/{notificationId}")
    fun getNotification(@Path("notificationId") notificationId: Long): Single<Response<Notification>>


    @GET("notes/{noteId}")
    override fun getNote(@Path("noteId") noteId: Long): Single<Response<Note>>

    @GET("users/{userId}/notes")
    override fun getUserNotes(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<Note>>>

    @GET("users/{userId}/favorites")
    override fun favoriteNotes(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<Note>>>

    @GET("users/{userId}/followers")
    override fun followers(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<User>>>

    @GET("users/{userId}/followings")
    override fun followings(@Path("userId") userId: Long, @Query("page") page: Int): Single<Response<Page<User>>>

    @GET("tags")
    override fun searchTag(@Query("name") name: String): Single<Response<Page<Tag>>>

    @POST("notes/search-by-tag")
    override fun searchByTag(@Body conditions: List<List<String>>, @Query("page") page: Int): Single<Response<Page<Note>>>

    @GET("notes/{noteId}/favorites")
    override fun favoriteUsers(@Path("noteId") noteId: Long, @Query("page") page: Int): Single<Response<Page<User>>>

    @GET("notes/{noteId}/comments")
    override fun findComments(@Path("noteId") noteId: Long, @Query("page") page: Int): Single<Response<Page<Comment>>>

    @GET("notes/{noteId}/comments/{commentId}")
    override fun findComments(@Path("noteId") noteId: Long, @Path("commentId") commentId: Long, @Query("page") page: Int): Single<Response<Page<Comment>>>




}