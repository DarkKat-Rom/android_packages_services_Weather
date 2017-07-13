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

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.drawable.Drawable;

import net.darkkatroms.weather.Config;
import net.darkkatroms.weather.R;

public class ThemeUtil {
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

    public static boolean themeColorsDisabled(Context context) {
        final UiModeManager uiManager = (UiModeManager) context.getSystemService(
                Context.UI_MODE_SERVICE);
        return uiManager.getNightMode() == UiModeManager.MODE_NIGHT_NO_WHITEOUT
                || uiManager.getNightMode() == UiModeManager.MODE_NIGHT_YES_BLACKOUT;
    }

    public static int getThemeColors(Context context) {
        return Config.getThemeColors(context);
    }

    public static boolean applyThemeColors(Context context) {
        return !themeColorsDisabled(context) && getThemeColors(context) != THEME_COLOR_DEFAULT;
    }

    public static int getThemeOverlayResId(Context context) {
        int themeOverlayResId = 0;
        switch (getThemeColors(context)) {
            case THEME_COLOR_HOLO_BLUE:
                themeOverlayResId = R.style.ThemeOverlay_HoloBlue;
                break;
            case THEME_COLOR_BLUE:
                themeOverlayResId = R.style.ThemeOverlay_Blue;
                break;
            case THEME_COLOR_RED:
                themeOverlayResId = R.style.ThemeOverlay_Red;
                break;
            case THEME_COLOR_PINK:
                themeOverlayResId = R.style.ThemeOverlay_Pink;
                break;
            case THEME_COLOR_PURPLE:
                themeOverlayResId = R.style.ThemeOverlay_Purple;
                break;
            case THEME_COLOR_INDIGO:
                themeOverlayResId = R.style.ThemeOverlay_Indigo;
                break;
            case THEME_COLOR_CYAN:
                themeOverlayResId = R.style.ThemeOverlay_Cyan;
                break;
            case THEME_COLOR_TEAL:
                themeOverlayResId = R.style.ThemeOverlay_Teal;
                break;
            case THEME_COLOR_GREEN:
                themeOverlayResId = R.style.ThemeOverlay_Green;
                break;
            case THEME_COLOR_LIME:
                themeOverlayResId = R.style.ThemeOverlay_Lime;
                break;
            case THEME_COLOR_YELLOW:
                themeOverlayResId = R.style.ThemeOverlay_Yellow;
                break;
            case THEME_COLOR_AMBER:
                themeOverlayResId = R.style.ThemeOverlay_Amber;
                break;
            case THEME_COLOR_ORANGE:
                themeOverlayResId = R.style.ThemeOverlay_Orange;
                break;
            case THEME_COLOR_BLUE_GREY:
                themeOverlayResId = R.style.ThemeOverlay_BlueGrey;
                break;
            default:
                break;
        }
        return themeOverlayResId;
    }
}
