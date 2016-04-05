package com.cs428.pandemic.frontEnd.gamePlay.boardFragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.enums.Role;

import java.util.HashMap;
import java.util.List;
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
    private List<UI_Player> players;
    private Canvas canvas;
    private CityParser cityParser;
    private Cities cities;
    private int width;
    private int height;

    // Relative icon offsets
    final float CONNECTION_OFFSET_X = 0.016667f;
    final float CONNECTION_OFFSET_Y = 0.029674f;
    final float CITY_SIZE_X = 0.033333f;
    final float CITY_SIZE_Y = 0.059347f;
    final float RESEARCH_STATION_SIZE_X = 0.016667f;
    final float RESEARCH_STATION_SIZE_Y = 0.029674f;
    final float RESEARCH_STATION_OFFSET_X = 0.013889f;
    final float RESEARCH_STATION_OFFSET_Y = 0.024728f;
    final float PAWN_SIZE_X = 0.008333f;
    final float PAWN_SIZE_Y = 0.029674f;


    public BoardDrawer(Resources res, IModelInterface modelFacade, List<UI_Player> players) {
        this.resources = res;
        this.modelFacade = modelFacade;
        this.players = players;
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
        System.out.println(width);
        System.out.println(height);
    }

    private void drawCityConnections(Map<String, UI_City> cityData) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);

        for (Map.Entry<String, UI_City> entry : cityData.entrySet()) {
            String city = entry.getKey();
            UI_City data = entry.getValue();

            float startX = (cities.getRelativeX(city) + CONNECTION_OFFSET_X) * width;
            float startY = (cities.getRelativeY(city) + CONNECTION_OFFSET_Y) * height;

            for (String neighbor : data.getNeighbors()) {
                float endX = (cities.getRelativeX(neighbor) + CONNECTION_OFFSET_X) * width;
                float endY = (cities.getRelativeY(neighbor) + CONNECTION_OFFSET_Y) * height;

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

            float x = (cities.getRelativeX(city)) * width;
            float y = (cities.getRelativeY(city)) * height;

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
            cityBitmap = Bitmap.createScaledBitmap(cityBitmap, (int) (CITY_SIZE_X * width), (int) (CITY_SIZE_Y * height), false);
            canvas.drawBitmap(cityBitmap, x, y, null);
        }
    }

    private void drawPlayerPawns() {
        System.out.println("IN DRAW PLAYER PAWNS");
        Map<Integer, String> playerLocations = modelFacade.getPawnLocations();

        // Keep track of how many players are in each city
        Map<String, Integer> numPlayersInCity = new HashMap<>();

        for (Map.Entry<Integer, String> entry : playerLocations.entrySet()) {
            int playerId = entry.getKey();
            String playerLocation = entry.getValue();

            // Keep track of how many players are in each city since that will affect the offsets
            // by which they're drawn.
            int count = numPlayersInCity.containsKey(playerLocation) ? numPlayersInCity.get(playerLocation) + 1 : 1;
            numPlayersInCity.put(playerLocation, count);

            // 15 x 30

            UI_Player player = getPlayerById(playerId);
            int resId = getRoleResId(player != null ? player.getPlayerRole() : null);

            // Determine player offset by the number of players already in their city
            float xOffset;
            float yOffset;
            if (count == 1) {
                xOffset = 0.0125f;
                yOffset = 0.0f;
            } else if (count == 2) {
                xOffset = 0.018056f;
                yOffset = 0.004946f;
            } else if (count == 3) {
                xOffset = 0.006944f;
                yOffset = 0.004946f;
            } else {
                xOffset = 0.0125f;
                yOffset = 0.009891f;
            }

            float x = (cities.getRelativeX(playerLocation) + xOffset) * width;
            float y = (cities.getRelativeY(playerLocation) + yOffset) * height;

            System.out.println("DRAWING PAWN WITH RES ID = " + resId);
            Bitmap playerPawnBitmap = BitmapFactory.decodeResource(resources, resId);
            playerPawnBitmap = Bitmap.createScaledBitmap(playerPawnBitmap, (int) (PAWN_SIZE_X * width), (int) (PAWN_SIZE_Y * height), false);
            canvas.drawBitmap(playerPawnBitmap, x, y, null);
        }
    }

    private void drawResearchStations() {
        List<String> researchStationLocations = modelFacade.getResearchStationLocations();

        Bitmap researchStationBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_researchstation);
        researchStationBitmap = Bitmap.createScaledBitmap(researchStationBitmap, (int) (RESEARCH_STATION_SIZE_X * width), (int) (RESEARCH_STATION_SIZE_Y * height), false);

        float x;
        float y;
        for (String researchStationLocation : researchStationLocations) {
            x = (cities.getRelativeX(researchStationLocation) + RESEARCH_STATION_OFFSET_X) * width;
            y = (cities.getRelativeY(researchStationLocation) + RESEARCH_STATION_OFFSET_Y) * height;
            canvas.drawBitmap(researchStationBitmap, x, y, null);
        }
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

    private UI_Player getPlayerById(int id) {
        for (UI_Player player : players) {
            if (player.getPlayerID() == id)
                return player;
        }
        return null;
    }

    private int getRoleResId(Role role) {
        int resId;
        switch (role) {
            case MEDIC:
                resId = R.drawable.piece_playerpawn_medic;
                break;
            case RESEARCHER:
                resId = R.drawable.piece_playerpawn_researcher;
                break;
            case SCIENTIST:
                resId = R.drawable.piece_playerpawn_scientist;
                break;
            case DISPATCHER:
                resId = R.drawable.piece_playerpawn_dispatcher;
                break;
            case OPERATIONS_EXPERT:
                resId = R.drawable.piece_playerpawn_operationsexpert;
                break;
            case QUARANTINE_SPECIALIST:
                resId = R.drawable.piece_playerpawn_quarantinespecialist;
                break;
            case CONTINGENCY_PLANNER:
                resId = R.drawable.piece_playerpawn_contingencyplanner;
                break;
            default:
                resId = -1;
        }
        return resId;
    }
}
