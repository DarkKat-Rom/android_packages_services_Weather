/*
 *  Copyright (C) 2017 DarkKat
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
package net.darkkatroms.weather.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import net.darkkatroms.weather.Config;
import net.darkkatroms.weather.R;

public class ThemeUtil {
    public static final int THEME_DARKKAT        = 0;
    public static final int THEME_MATERIAL       = 1;
    public static final int THEME_MATERIAL_LIGHT = 2;
    public static final int THEME_BLACKOUT       = 3;
    public static final int THEME_WHITEOUT       = 4;

    public static final int THEME_COLOR_DEFAULT   = 0;
    public static final int THEME_COLOR_HOLO_BLUE = 1;
    public static final int THEME_COLOR_BLUE      = 2;
    public static final int THEME_COLOR_RED       = 3;
    public static final int THEME_COLOR_PINK      = 4;
    public static final int THEME_COLOR_PURPLE    = 5;
    public static final int THEME_COLOR_INDIGO    = 6;
    public static final int THEME_COLOR_CYAN      = 7;
    public static final int THEME_COLOR_TEAL      = 8;
    public static final int THEME_COLOR_GREEN     = 9;
    public static final int THEME_COLOR_LIME      = 10;
    public static final int THEME_COLOR_YELLOW    = 11;
    public static final int THEME_COLOR_AMBER     = 12;
    public static final int THEME_COLOR_ORANGE    = 13;
    public static final int THEME_COLOR_BLUE_GREY = 14;


    public static int getTheme(Context context) {
        return Config.getTheme(context);
    }

    public static boolean isThemeMaterialLight(Context context) {
        return getTheme(context) == THEME_MATERIAL_LIGHT;
    }

    public static boolean isBlackoutTheme(Context context) {
        return getTheme(context) == THEME_BLACKOUT;
    }

    public static boolean isWhiteoutTheme(Context context) {
        return getTheme(context) == THEME_WHITEOUT;
    }

    public static boolean isThemeLight(Context context) {
        return getTheme(context) == THEME_MATERIAL_LIGHT
                || getTheme(context) == THEME_WHITEOUT;
    }

    public static int getThemeColors(Context context) {
        return Config.getThemeColors(context);
    }

    public static int getAppThemeResId(Context context) {
        int themeResId = 0;
        switch (getTheme(context)) {
            case THEME_DARKKAT:
                themeResId = R.style.AppTheme_DarkKat;
                break;
            case THEME_MATERIAL:
                themeResId = R.style.AppTheme;
                break;
            case THEME_BLACKOUT:
                themeResId = R.style.AppTheme_Blackout;
                break;
            case THEME_WHITEOUT:
                themeResId = R.style.AppTheme_Whiteout;
                break;
            default:
                break;
        }
        return themeResId;
    }

    public static int getDetailedWeatherThemeResId(Context context) {
        int themeResId = 0;
        switch (getTheme(context)) {
            case THEME_DARKKAT:
                themeResId = R.style.AppTheme_DarkKat_DetailedWeather;
                break;
            case THEME_MATERIAL:
                themeResId = R.style.AppTheme_DetailedWeather;
                break;
            case THEME_BLACKOUT:
                themeResId = R.style.AppTheme_Blackout_DetailedWeather;
                break;
            case THEME_WHITEOUT:
                themeResId = R.style.AppTheme_Whiteout_DetailedWeather;
                break;
            default:
                break;
        }
        return themeResId;
    }

    public static int getAppThemeOverlayResId(Context context) {
        int themeOverlayResId = 0;
        switch (getThemeColors(context)) {
            case THEME_COLOR_HOLO_BLUE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_HoloBlue;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_HoloBlue_Light;
                }
                break;
            case THEME_COLOR_BLUE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Blue;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Blue_Light;
                }
                break;
            case THEME_COLOR_RED:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Red;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Red_Light;
                }
                break;
            case THEME_COLOR_PINK:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Pink;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Pink_Light;
                }
                break;
            case THEME_COLOR_PURPLE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Purple;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Purple_Light;
                }
                break;
            case THEME_COLOR_INDIGO:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Indigo;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Indigo_Light;
                }
                break;
            case THEME_COLOR_CYAN:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Cyan;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Cyan_Light;
                }
                break;
            case THEME_COLOR_TEAL:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Teal;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Teal_Light;
                }
                break;
            case THEME_COLOR_GREEN:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Green;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Green_Light;
                }
                break;
            case THEME_COLOR_LIME:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Lime;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Lime_Light;
                }
                break;
            case THEME_COLOR_YELLOW:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Yellow;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Yellow_Light;
                }
                break;
            case THEME_COLOR_AMBER:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Amber;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Amber_Light;
                }
                break;
            case THEME_COLOR_ORANGE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Orange;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_Orange_Light;
                }
                break;
            case THEME_COLOR_BLUE_GREY:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_BlueGrey;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_AppTheme_BlueGrey_Light;
                }
                break;
            default:
                break;
        }
        return themeOverlayResId;
    }

    public static int getDetailedWeatherThemeOverlayResId(Context context) {
        int themeOverlayResId = 0;
        switch (getThemeColors(context)) {
            case THEME_COLOR_HOLO_BLUE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_HoloBlue;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_HoloBlue_Light;
                }
                break;
            case THEME_COLOR_BLUE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Blue;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Blue_Light;
                }
                break;
            case THEME_COLOR_RED:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Red;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Red_Light;
                }
                break;
            case THEME_COLOR_PINK:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Pink;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Pink_Light;
                }
                break;
            case THEME_COLOR_PURPLE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Purple;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Purple_Light;
                }
                break;
            case THEME_COLOR_INDIGO:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Indigo;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Indigo_Light;
                }
                break;
            case THEME_COLOR_CYAN:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Cyan;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Cyan_Light;
                }
                break;
            case THEME_COLOR_TEAL:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Teal;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Teal_Light;
                }
                break;
            case THEME_COLOR_GREEN:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Green;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Green_Light;
                }
                break;
            case THEME_COLOR_LIME:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Lime;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Lime_Light;
                }
                break;
            case THEME_COLOR_YELLOW:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Yellow;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Yellow_Light;
                }
                break;
            case THEME_COLOR_AMBER:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Amber;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Amber_Light;
                }
                break;
            case THEME_COLOR_ORANGE:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Orange;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_Orange_Light;
                }
                break;
            case THEME_COLOR_BLUE_GREY:
                if (!isThemeLight(context)) {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_BlueGrey;
                } else {
                    themeOverlayResId = R.style.ThemeOverlay_DetailedWeather_BlueGrey_Light;
                }
                break;
            default:
                break;
        }
        return themeOverlayResId;
    }

    public static int getActionBarBgColor(Context context) {
        int colorResId = 0;
        switch (getTheme(context)) {
            case THEME_DARKKAT:
            case THEME_MATERIAL:
            case THEME_MATERIAL_LIGHT:
                colorResId = R.color.theme_primary;
                break;
            case THEME_BLACKOUT:
                colorResId = R.color.theme_primary_blackout;
                break;
            case THEME_WHITEOUT:
                colorResId = R.color.theme_primary_whiteout;
                break;
            default:
                break;
        }
        return context.getColor(colorResId);
    }

    public static int getTabUnderlineColor(Context context) {
        int colorResId = 0;
        switch (getTheme(context)) {
            case THEME_DARKKAT:
            case THEME_MATERIAL:
            case THEME_MATERIAL_LIGHT:
            case THEME_BLACKOUT:
                colorResId = R.color.tab_selected_underline_color;
                break;
            case THEME_WHITEOUT:
                colorResId = R.color.tab_selected_underline_color_whiteout;
                break;
            default:
                break;
        }
        return context.getColor(colorResId);
    }
}
