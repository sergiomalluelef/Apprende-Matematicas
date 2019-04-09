package com.example.samsung.game.Fragments;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.samsung.game.MainDibujar;
import com.example.samsung.game.MainMenus;
import com.example.samsung.game.R;
import com.example.samsung.game.Singleton;
import com.example.samsung.game.Sonido;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubMenusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubMenusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubMenusFragment extends Fragment implements View.OnTouchListener {


    ImageView imgUnidadUno, imgnumerodos;
    ImageView imgdosbloqueado, imgtresbloqueado, imgcuatrobloqueado, imgcincobloqueado;
    Sonido sonido;

    MainMenus mainMenus;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SubMenusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubMenusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubMenusFragment newInstance(String param1, String param2) {
        SubMenusFragment fragment = new SubMenusFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_menus, container, false);
        sonido = new Sonido(getActivity());
        mainMenus = new MainMenus();
        imgUnidadUno = (ImageView) view.findViewById(R.id.imgUnidadUno);
        imgnumerodos = (ImageView) view.findViewById(R.id.imgnumdos);
        imgUnidadUno.setOnTouchListener(this);
        imgnumerodos.setOnTouchListener(this);


        if (Singleton.getInstancia(getActivity()).unidad1 == 1) {
            imgdosbloqueado = (ImageView) view.findViewById(R.id.imgdosbloqueado);
            imgtresbloqueado = (ImageView) view.findViewById(R.id.imgtresbloqueado);
            imgcuatrobloqueado = (ImageView) view.findViewById(R.id.imgcuatrobloqueado);
            imgcincobloqueado = (ImageView) view.findViewById(R.id.imgcincobloqueado);

            imgdosbloqueado.setOnTouchListener(this);
            imgtresbloqueado.setOnTouchListener(this);
            imgcuatrobloqueado.setOnTouchListener(this);
            imgcincobloqueado.setOnTouchListener(this);
        }

        if (Singleton.getInstancia(getActivity()).unidad1 == 2) {
            imgdosbloqueado = (ImageView) view.findViewById(R.id.imgdosbloqueado);
            imgtresbloqueado = (ImageView) view.findViewById(R.id.imgtresbloqueado);
            imgcuatrobloqueado = (ImageView) view.findViewById(R.id.imgcuatrobloqueado);
            imgcincobloqueado = (ImageView) view.findViewById(R.id.imgcincobloqueado);

            imgdosbloqueado.setVisibility(View.INVISIBLE);


            imgtresbloqueado.setOnTouchListener(this);
            imgcuatrobloqueado.setOnTouchListener(this);
            imgcincobloqueado.setOnTouchListener(this);
        }
        if (Singleton.getInstancia(getActivity()).unidad1 == 3) {
            imgdosbloqueado = (ImageView) view.findViewById(R.id.imgdosbloqueado);
            imgtresbloqueado = (ImageView) view.findViewById(R.id.imgtresbloqueado);
            imgcuatrobloqueado = (ImageView) view.findViewById(R.id.imgcuatrobloqueado);
            imgcincobloqueado = (ImageView) view.findViewById(R.id.imgcincobloqueado);

            imgdosbloqueado.setVisibility(View.INVISIBLE);
            imgtresbloqueado.setVisibility(View.INVISIBLE);


            imgcuatrobloqueado.setOnTouchListener(this);
            imgcincobloqueado.setOnTouchListener(this);
        }
        if (Singleton.getInstancia(getActivity()).unidad1 == 4) {
            imgdosbloqueado = (ImageView) view.findViewById(R.id.imgdosbloqueado);
            imgtresbloqueado = (ImageView) view.findViewById(R.id.imgtresbloqueado);
            imgcuatrobloqueado = (ImageView) view.findViewById(R.id.imgcuatrobloqueado);
            imgcincobloqueado = (ImageView) view.findViewById(R.id.imgcincobloqueado);

            imgdosbloqueado.setVisibility(View.INVISIBLE);
            imgtresbloqueado.setVisibility(View.INVISIBLE);
            imgcuatrobloqueado.setVisibility(View.INVISIBLE);


            imgcincobloqueado.setOnTouchListener(this);
        }
        if (Singleton.getInstancia(getActivity()).unidad1 == 5) {
            imgdosbloqueado = (ImageView) view.findViewById(R.id.imgdosbloqueado);
            imgtresbloqueado = (ImageView) view.findViewById(R.id.imgtresbloqueado);
            imgcuatrobloqueado = (ImageView) view.findViewById(R.id.imgcuatrobloqueado);
            imgcincobloqueado = (ImageView) view.findViewById(R.id.imgcincobloqueado);

            imgdosbloqueado.setVisibility(View.INVISIBLE);
            imgtresbloqueado.setVisibility(View.INVISIBLE);
            imgcuatrobloqueado.setVisibility(View.INVISIBLE);
            imgcincobloqueado.setVisibility(View.INVISIBLE);
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

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (imgUnidadUno == v) {
                sonido.playSound("click");
                Intent intent = new Intent(getActivity(), MainDibujar.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.transicion_entrada, R.anim.transicion_salida);
            }
            if (imgnumerodos == v){

                if (Singleton.getInstancia(getActivity()).unidad1 == 2) {
                    sonido.playSound("click");
                    Intent intent = new Intent(getActivity(), MainDibujar.class);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.transicion_entrada, R.anim.transicion_salida);
                }
            }
            if(imgdosbloqueado == v){
                sonido.playSound("error");
                mainMenus.clickIf(imgdosbloqueado);
            }
            if(imgtresbloqueado == v){
                sonido.playSound("error");
                mainMenus.clickIf(imgtresbloqueado);
            }
            if(imgcuatrobloqueado == v){
                sonido.playSound("error");
                mainMenus.clickIf(imgcuatrobloqueado);
            }

            if(imgcincobloqueado == v){
                sonido.playSound("error");
                mainMenus.clickIf(imgcincobloqueado);
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
