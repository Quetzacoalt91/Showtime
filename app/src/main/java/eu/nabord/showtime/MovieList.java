/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package eu.nabord.showtime;

import android.util.Log;

import com.muparse.M3UItem;
import com.muparse.M3UParser;
import com.muparse.M3UPlaylist;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class MovieList {

    public static final String PLAYLIST_URL = "http://192.168.0.29:3001/playlist";
    public static final String TAG = "MovieList";

    private static List<Movie> list;
    private static long count = 0;

    public static List<Movie> getList() {
        if (list == null) {
            list = setupMovies();
        }
        return list;
    }

    public static List<Movie> setupMovies() {

        String dummyDescription = "Fusce id nisi turpis. Praesent viverra bibendum semper. "
                + "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est "
                + "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit "
                + "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit "
                + "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id "
                + "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat.";
        String bgImageUrl = "https://presentationarchive.com/e42013/out-b.jpg";
        String cardImageUrl = "https://d24j9r7lck9cin.cloudfront.net/l/o/1/1122.1539639917.png";

        M3UPlaylist channels;
        try {
            channels = new M3UParser().parseFile(
                    new BufferedInputStream(
                            new URL(PLAYLIST_URL).openStream()
                    )
            );
        } catch (Exception e) {
            Log.e(TAG, "Cannot get channels", e);
            return new ArrayList<>();
        }

        list = new ArrayList<>();

        for (M3UItem channel : channels.getPlaylistItems()) {
            list.add(
                    buildMovieInfo(
                            channel.getItemName(),
                            dummyDescription,
                            "",
                            channel.getItemUrl(),
                            channel.getItemIcon(),
                            bgImageUrl
                    )
            );
        }

        return list;
    }

    private static Movie buildMovieInfo(
            String title,
            String description,
            String channel,
            String videoUrl,
            String cardImageUrl,
            String backgroundImageUrl) {
        Movie movie = new Movie();
        movie.setId(count++);
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setChannel(channel);
        movie.setCardImageUrl(cardImageUrl);
        movie.setBackgroundImageUrl(backgroundImageUrl);
        movie.setVideoUrl(videoUrl);
        return movie;
    }
}