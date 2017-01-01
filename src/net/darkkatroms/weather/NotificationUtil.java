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
import android.app.Notification.Action;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RemoteViews;
import android.widget.TextView;

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
        boolean showDKIcon =  Config.getNotificationShowDKIcon(mContext);
        boolean showSecure =  Config.getNotificationShowSecure(mContext);

        Notification.Builder builder = new Notification.Builder(mContext)
            .setShowWhen(true)
            .setWhen(System.currentTimeMillis())
            .setOngoing(true)
            .setStyle(new Notification.DecoratedCustomViewStyle())
            .setCustomContentView(getCollapsedContent(info))
            .setCustomBigContentView(getExpandedContent(info))
            .setColor(0xff009688)
            .addAction(getSettingsAction());

        if (showDKIcon) {
            builder.setSmallIcon(R.drawable.ic_dk);
        } else {
            builder.setSmallIcon(textAsSmallIcon(info.getTemperature(), info.getFormattedTemperature()));
        }

        if (showSecure) {
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
        }

        return builder.build();
    }

    private Icon textAsSmallIcon(String textSmall, String textLarge) {
        int iconSize = mResources.getDimensionPixelSize(R.dimen.notification_small_icon_size);
        int maxTextWidth = iconSize;
        int maxTextHeight = mResources.getDimensionPixelSize(R.dimen.notification_small_icon_max_text_height);
        int iconColor = mContext.getColor(R.color.notification_small_icon_color);
        String usedText = textLarge;
        float textSize = 0f;
        float textHeight = 0f;
        float textX = iconSize * 0.5f;
        float textY = 0f;

        TextPaint paint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        Bitmap b = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        Rect bounds = new Rect();

        TextView tv = new TextView(mContext);
        paint.setTextAlign(TextPaint.Align.CENTER);

        tv.setTextAppearance(android.R.style.TextAppearance_Material_Notification_Info);
        Typeface tf = tv.getTypeface();
        paint.setTypeface(tv.getTypeface());
        paint.setColor(iconColor);

        do {
            paint.setTextSize(textSize++);
            paint.getTextBounds(usedText, 0, usedText.length(), bounds);
        } while (bounds.height() < maxTextHeight);
        paint.setTextSize(textSize);

        if (bounds.width() > maxTextWidth) {
            usedText = textSmall;
            paint.getTextBounds(usedText, 0, usedText.length(), bounds);
            if (bounds.width() > maxTextWidth) {
                do {
                    paint.setTextSize(textSize--);
                    paint.getTextBounds(usedText, 0, usedText.length(), bounds);
                } while (bounds.width() > maxTextWidth);
            }
        }
        paint.setTextSize(textSize);

        textHeight = -paint.getFontMetrics().ascent;
        textY = (iconSize + textHeight) * 0.47f;
        canvas.drawText(usedText, textX, textY, paint);

        return Icon.createWithBitmap(b);
    }

    private RemoteViews getCollapsedContent(WeatherInfo info) {
        Icon icon = getConditionIcon(info.getConditionCode());
        boolean showLocation =  Config.getNotificationShowLocation(mContext);
        String title = info.getFormattedTemperature() + " - " + info.getCondition();
        String text = showLocation ? info.getCity() : "";
        RemoteViews collapsedContent = new RemoteViews(mContext.getPackageName(),
                R.layout.notification_collapsed_content);

        collapsedContent.setOnClickPendingIntent(R.id.collapsed_content, getContentIntent(5, 0));
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
            dayContent.setOnClickPendingIntent(R.id.expanded_content_item, getContentIntent(i, i));

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

    private PendingIntent getContentIntent(int requestCode, int day) {
        Bundle b = new Bundle();
        b.putInt(DetailedWeatherActivity.DAY_INDEX, day);
        Intent intent = new Intent(mContext, DetailedWeatherActivity.class);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    private Action getSettingsAction() {
        String title = mResources.getString(R.string.action_settings_title);
        Intent intent = new Intent(mContext, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 6, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Action.Builder builder = new Action.Builder(R.drawable.ic_notification_action_settings,
                title, pendingIntent);
        return builder.build();
    }
}
