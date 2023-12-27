package com.example.base_de_donnees;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadImagesTask extends AsyncTask<Void, Void, List<ImageEntity>> {
    private final String type;
    private final Context context;

    public LoadImagesTask(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    protected List<ImageEntity> doInBackground(Void... voids) {
        // Chemin du dossier des images dans le dossier "assets"
        String assetsFolderPath = "images";
        AssetManager assetManager = context.getAssets();
        try {
            // Liste des fichiers dans le dossier "images" dans "assets"
            String[] files = assetManager.list(assetsFolderPath);

            if (files != null) {
                List<ImageEntity> imageEntities = new ArrayList<>();
                for (String fileName : files) {
                    // Chemin complet du fichier dans le dossier "assets/images"
                    String path = "file:///android_asset/" + assetsFolderPath + "/" + fileName;
                    ImageEntity image = new ImageEntity(path, type);
                    imageEntities.add(image);
                }

                // Insérer les chemins des images dans la base de données
                DatabaseInitializer.getInstance(context).imageDao().insertImages(imageEntities);

                Log.d("LoadImagesTask", "Images inserted successfully");
                return imageEntities;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<ImageEntity> imageEntities) {
        if (imageEntities != null && !imageEntities.isEmpty()) {
            Log.d("LoadImagesTask", "Images loaded successfully");
            // Configurer le ViewPager2 après avoir chargé les images
            ((CarouselActivity) context).setupViewPager(imageEntities);
        } else {
            Log.d("LoadImagesTask", "Failed to load images");
            // Gérer l'échec du chargement des images, si nécessaire
        }}
}
