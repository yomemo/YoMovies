/*
 * Developed by Hung Pham.
 * Email: admin@yomemo.com || numerotech@gmail.com.
 * Copyright (c) 2019. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *       https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yomemo.yomovies.utils


class AppConstants {
    companion object TMDB {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        // Image Path
        private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
        private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
        // Page Sizse
        const val PAGING_SIZE = 20
        // Youtube
        private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
        private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"
        // Database
        const val DATABASE_NAME = "YOMOVIES_DATABASE"
        const val PREF_NAME = "YO_PREF"
        const val KEY_GENRES = "KEY_GENRES"

        fun getPosterPath(posterPath: String?): String {
            return BASE_POSTER_PATH + posterPath
        }

        fun getBackdropPath(backdropPath: String?): String {
            return BASE_BACKDROP_PATH + backdropPath
        }

        fun getYoutubeVideoPath(videoPath: String?): String {
            return YOUTUBE_VIDEO_URL + videoPath
        }

        fun getYoutubeThumbnailPath(thumbnailPath: String?): String {
            return "$YOUTUBE_THUMBNAIL_URL$thumbnailPath/0.jpg"
        }
    }
}

