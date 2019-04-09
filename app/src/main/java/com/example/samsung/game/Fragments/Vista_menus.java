package com.example.samsung.game.Fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.samsung.game.MainMenus;
import com.example.samsung.game.R;
import com.example.samsung.game.Singleton;
import com.example.samsung.game.Sonido;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Vista_menus.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Vista_menus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Vista_menus extends Fragment implements View.OnTouchListener {

    ImageView unidadUno;
    RelativeLayout layout;
    Sonido sonido;
    ImageView imgunidaddos, imgunidadtres, imgunidadcuatro;

    MainMenus mainMenus;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Vista_menus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Vista_menus.
     */
    // TODO: Rename and change types and number of parameters
    public static Vista_menus newInstance(String param1, String param2) {
        Vista_menus fragment = new Vista_menus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vista_menus,container,false);
        mainMenus = new MainMenus();
        sonido = new Sonido(getActivity());
        unidadUno = (ImageView) view.findViewById(R.id.imageView3);
        unidadUno.setOnTouchListener(this);

        layout = (RelativeLayout) view.findViewById(R.id.framMenus);

        if (Singleton.getInstancia(getActivity()).unidad == 1) {
            imgunidaddos = (ImageView) view.findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) view.findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) view.findViewById(R.id.imageView10);

            imgunidaddos.setOnTouchListener(this);
            imgunidadtres.setOnTouchListener(this);
            imgunidadcuatro.setOnTouchListener(this);
        }

        if(Singleton.getInstancia(getActivity()).unidad == 2){
            imgunidaddos = (ImageView) view.findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) view.findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) view.findViewById(R.id.imageView10);

            imgunidadtres.setOnTouchListener(this);
            imgunidadcuatro.setOnTouchListener(this);

            imgunidaddos.setVisibility(View.INVISIBLE);
        }
        if(Singleton.getInstancia(getActivity()).unidad == 3){
            imgunidaddos = (ImageView) view.findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) view.findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) view.findViewById(R.id.imageView10);

            imgunidadcuatro.setOnTouchListener(this);

            imgunidaddos.setVisibility(View.INVISIBLE);
            imgunidadtres.setVisibility(View.INVISIBLE);
        }
        if(Singleton.getInstancia(getActivity()).unidad == 4){
            imgunidaddos = (ImageView) view.findViewById(R.id.imageView7);
            imgunidadtres = (ImageView) view.findViewById(R.id.imageView9);
            imgunidadcuatro = (ImageView) view.findViewById(R.id.imageView10);

            imgunidaddos.setVisibility(View.INVISIBLE);
            imgunidadtres.setVisibility(View.INVISIBLE);
            imgunidadcuatro.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        SubMenusFragment fragment = new SubMenusFragment();

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            if (unidadUno == v )
            {
                sonido.playSound("click");
                layout.removeAllViews();

                transaction.add(R.id.frameMenus, fragment);

                transaction.commit();
            }
            if (imgunidaddos == v) {
                sonido.playSound("error");
                mainMenus.clickIf(imgunidaddos);

            }
            if (imgunidadtres == v) {
                sonido.playSound("error");
                mainMenus.clickIf(imgunidadtres);

            }
            if (imgunidadcuatro == v) {
                sonido.playSound("error");
                mainMenus.clickIf(imgunidadcuatro);

            }

        }
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
