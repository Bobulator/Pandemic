package com.cs428.pandemic.frontEnd.gamePlay.boardFragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.cs428.pandemic.BuildConfig;
import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;

import java.util.Map;

/**
 * This class will handle drawing all of the components of the board, including the board itself,
 * cities, city connections, disease, built research stations, and pawn locations.
 *
 * Created by Chad on 3/24/2016.
 */
public class BoardDrawer {

    private Resources resources;
    private IModelInterface modelFacade;
    private Canvas canvas;
    private CityParser cityParser;
    private Cities cities;
    private int width;
    private int height;

    public BoardDrawer(Resources res, IModelInterface modelFacade) {
        this.resources = res;
        this.modelFacade = modelFacade;
    }

    public Bitmap createBitmap(int resId, int reqWidth, int reqHeight) {
        return decodeSampledBitmapFromResource(resources, resId, reqWidth, reqHeight);
    }

    public Canvas drawBoard(Bitmap bitmap) {

        initDrawer(bitmap);

        // Draw the initial board
        canvas.drawBitmap(bitmap, 0, 0, null);

        // Retrieve City locations
        cities = cityParser.parseXML();

        // Retrieve city data from model
        Map<String, UI_City> cityData = modelFacade.getCityData();

        // Draw everything that lies 'on' the board, with each drawing overwriting any drawing that
        // came before it.
        drawCityConnections(cityData);
        drawCities(cityData);
        drawPlayerPawns();
        drawResearchStations();

        return canvas;
    }

    private void initDrawer(Bitmap bitmap) {
        canvas = new Canvas(bitmap);
        cityParser = new CityParser(resources.getXml(R.xml.relative_city_locations));
        width = canvas.getWidth();
        height = canvas.getHeight();
    }

    private void drawCityConnections(Map<String, UI_City> cityData) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);

        for (Map.Entry<String, UI_City> entry : cityData.entrySet()) {
            String city = entry.getKey();
            UI_City data = entry.getValue();

            float startX = (float) (cities.getRelativeX(city) * width) + 30f;
            float startY = (float) (cities.getRelativeY(city) * height) + 30f;

            for (String neighbor : data.getNeighbors()) {
                float endX = (float) (cities.getRelativeX(neighbor) * width) + 30f;
                float endY = (float) (cities.getRelativeY(neighbor) * height) + 30f;

                // Is the edge supposed to go offscreen?
                if ((endX - startX) > (width / 2)) {
                    endX = 0;
                    endY = startY + ((endY - startY) / 2);
                } else if ((endX - startX) < -(width / 2)) {
                    endX = width;
                    endY = startY + ((endY - startY) / 2);
                }
                canvas.drawLine(startX, startY, endX, endY, p);
            }
        }
    }

    private void drawCities(Map<String, UI_City> cityData) {
        for (Map.Entry<String, UI_City> entry : cityData.entrySet()) {
            String city = entry.getKey();
            UI_City data = entry.getValue();

            float x = (float) ((cities.getRelativeX(city)) * width);
            float y = (float) ((cities.getRelativeY(city)) * height);

            int resId = -1;
            switch (data.getDiseaseColor()) {
                case BLACK:
                    resId = R.drawable.icon_city_black_less;
                    break;
                case BLUE:
                    resId = R.drawable.icon_city_blue_less;
                    break;
                case RED:
                    resId = R.drawable.icon_city_red_less;
                    break;
                case YELLOW:
                    resId = R.drawable.icon_city_yellow_less;
                    break;
            }
            Bitmap cityBitmap = BitmapFactory.decodeResource(resources, resId);
            cityBitmap = Bitmap.createScaledBitmap(cityBitmap, 60, 60, false);
            canvas.drawBitmap(cityBitmap, x, y, null);
        }
    }

    private void drawPlayerPawns() {

    }

    private void drawResearchStations() {

    }

    /**
     * Construct a Bitmap of the given resource that is as small as possible while remaining
     * larger than the given width and height.
     *
     * @param res The resources from which the image is located.
     * @param resId The specific id of the image to generate a Bitmap for.
     * @param reqWidth The width constraint.
     * @param reqHeight The height constraint.
     * @return A Bitmap such that the width and height are as small as possible while remaining
     * larger than the reqWidth and reqHeight.
     */
    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                   int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inMutable = true;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Determine the extent to which the Bitmap dimensions should be reduced from the original image
     * size using the given constraints. The reduction is determined such that the original image is
     * reduced to be as small as possible while remaining larger than the given width and height.
     *
     * @param options The Bitmap options from which the original image dimensions are retrieved.
     * @param reqWidth The width constraint.
     * @param reqHeight The height constraint.
     *
     * @return The sample size by which the original image should be reduced.
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw width and height of the image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // width and height larger than the requested width and height.
        while ((height / inSampleSize) > reqHeight
                && (width / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
        inSampleSize /= 2;

        return inSampleSize;
    }
}
