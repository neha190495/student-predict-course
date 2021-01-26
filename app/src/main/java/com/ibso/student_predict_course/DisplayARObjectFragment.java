package com.ibso.student_predict_course;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class DisplayARObjectFragment extends Fragment {

    private static final String TAG = DisplayARObjectFragment.class.getSimpleName();
    //Create a member variable for ModelRenderable//
    private ModelRenderable objectRenderable;

    //Create a member variable for ArFragment//
    private ArFragment arCoreFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_ar_objects, container, false);

        //Find the fragment, using fragment manager//
        arCoreFragment = (ArFragment) getChildFragmentManager().findFragmentById(R.id.display_ar_fragment);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //Build the ModelRenderable//
            ModelRenderable.builder()
                    .setSource(getContext(), R.raw.methane)
                    .build()
                    .thenAccept(renderable -> objectRenderable = renderable)
                    .exceptionally(
                            //If an error occurs...//
                            throwable -> {
                                //...then print the following message to Logcat//
                                Log.e(TAG, "Unable to load renderable");
                                return null;
                            });
        }
        //Listen for onTap events//
        arCoreFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (objectRenderable == null) {
                        return;
                    }
                    Anchor anchor = hitResult.createAnchor();
                    //Build a node of type AnchorNode//
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    //Connect the AnchorNode to the Scene//
                    anchorNode.setParent(arCoreFragment.getArSceneView().getScene());
                    //Build a node of type TransformableNode//
                    TransformableNode transformableNode = new TransformableNode(arCoreFragment.getTransformationSystem());
                    transformableNode.getScaleController().setMaxScale(0.5f);
                    transformableNode.getScaleController().setMinScale(0.1f);
                    transformableNode.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 240f));
                    //Connect the TransformableNode to the AnchorNode//
                    transformableNode.setParent(anchorNode);
                    //Attach the Renderable//
                    transformableNode.setRenderable(objectRenderable);
                    //Set the node//
                    transformableNode.select();
                });
        return view;
    }
}
