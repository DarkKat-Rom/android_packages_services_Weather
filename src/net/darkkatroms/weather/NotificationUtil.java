/*
 *  Copyright (C) 2015 The OmniROM Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.darkkatroms.weather;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.widget.RemoteViews;

import net.darkkatroms.weather.WeatherInfo.DayForecast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class NotificationUtil {
    public static final int NOTIFICATION_ID = 1;

    private final Context mContext;
    private final Resources mResources;

    public NotificationUtil(Context context) {
        mContext = context;
        mResources = context.getResources();
    }

    public void sendNotification() {
        ((NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(NOTIFICATION_ID, createNotification());
    }

    private Notification createNotification() {
        WeatherInfo info =  Config.getWeatherData(mContext);

        Notification.Builder builder = new Notification.Builder(mContext)
            .setShowWhen(true)
            .setWhen(System.currentTimeMillis())
            .setOngoing(true)
            .setSmallIcon(textAsSmallIcon(info.getTemperature()))
            .setStyle(new Notification.DecoratedCustomViewStyle())
            .setCustomContentView(getCollapsedContent(info))
            .setCustomBigContentView(getExpandedContent(info))
            .setContentIntent(getContentIntent())
            .setColor(0xff009688);

        return builder.build();
    }

    private Icon textAsSmallIcon(String text) {
        int iconSize = mResources.getDimensionPixelSize(R.dimen.notification_small_icon_size);
        float desiredTextDimension = mResources.getDimension(R.dimen.notification_small_icon_size);
        float textSize = 0;
        float textHeight = 0;
        float textX = iconSize * 0.5f;
        float textY = 0;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap b = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        Rect bounds = new Rect();

        paint.setTextAlign(Paint.Align.CENTER);
        do {
            paint.setTextSize(textSize++);
        } while (paint.measureText(text) < desiredTextDimension);
        paint.setTextSize(textSize);
        paint.setColor(0xffffffff);

        textHeight = -paint.getFontMetrics().ascent;
        textY = (iconSize + textHeight) * 0.47f;
        canvas.drawText(text, textX, textY, paint);

        return Icon.createWithBitmap(b);
    }

    private RemoteViews getCollapsedContent(WeatherInfo info) {
        Icon icon = getConditionIcon(info.getConditionCode());
        String title = info.getFormattedTemperature() + " - " + info.getCondition();
        String text = info.getCity();
        RemoteViews collapsedContent = new RemoteViews(mContext.getPackageName(),
                R.layout.notification_collapsed_content);

        collapsedContent.setImageViewIcon(R.id.content_image, icon);
        collapsedContent.setTextViewText(R.id.content_title, title);
        collapsedContent.setTextViewText(R.id.content_text, text);
        return collapsedContent;
    }

    private RemoteViews getExpandedContent(WeatherInfo info) {
        TimeZone myTimezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(myTimezone);
        ArrayList<DayForecast> forecasts = (ArrayList) info.getForecasts();
        RemoteViews expandedContent = new RemoteViews(mContext.getPackageName(),
                R.layout.notification_expanded_content);

        for (int i = 0; i < 5; i++) {
            RemoteViews dayContent = new RemoteViews(mContext.getPackageName(),
                    R.layout.notification_expanded_content_item);
            DayForecast d = forecasts.get(i);
            String dayName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT,
                    Locale.getDefault());
            Icon icon = getConditionIcon(d.getConditionCode());
            String dayTemps = d.getFormattedLow() + " | " + d.getFormattedHigh();

            dayContent.setTextViewText(R.id.content_item_day, dayName);
            dayContent.setImageViewIcon(R.id.content_item_image, icon);
            dayContent.setTextViewText(R.id.content_item_temp, dayTemps);

            calendar.roll(Calendar.DAY_OF_WEEK, true);
            expandedContent.addView(R.id.expanded_content, dayContent);
        }
        return expandedContent;
    }

    private Icon getConditionIcon(int conditionCode) {
        int iconResid = mResources.getIdentifier(
                "weather_" + conditionCode, "drawable", mContext.getPackageName());
        Icon icon = Icon.createWithResource(mContext, iconResid);
        icon.setTint(0xff009688);
        return icon;
    }

    private PendingIntent getContentIntent() {
        Intent intent = new Intent(mContext, DetailedWeatherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}
