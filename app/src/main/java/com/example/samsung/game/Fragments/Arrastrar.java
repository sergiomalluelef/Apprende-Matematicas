package com.example.samsung.game.Fragments;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsung.game.MainDibujar;
import com.example.samsung.game.R;
import com.example.samsung.game.Singleton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Arrastrar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Arrastrar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Arrastrar extends Fragment {

    Button btn1, btn2, btn3, btn4, test1, test2, test3, test4;
    LinearLayout target1, target2, target3, target4;
    int i = 0;
    public static int nivel = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Arrastrar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Arrastrar.
     */
    // TODO: Rename and change types and number of parameters
    public static Arrastrar newInstance(String param1, String param2) {
        Arrastrar fragment = new Arrastrar();
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
        View view = inflater.inflate(R.layout.fragment_arrastrar, container, false);

        Singleton.getInstancia(getActivity());

        if (Singleton.getInstancia(getActivity()).etapas == 2) {

            target1 = (LinearLayout) view.findViewById(R.id.target1);

            test1 = (Button) view.findViewById(R.id.test1);

            btn1 = (Button) view.findViewById(R.id.btn1);
            btn2 = (Button) view.findViewById(R.id.btn2);
            btn3 = (Button) view.findViewById(R.id.btn3);
            btn4 = (Button) view.findViewById(R.id.btn4);

            target1.setOnDragListener(dragListener);

            btn1.setOnLongClickListener(onLongClickListener);
            btn2.setOnLongClickListener(onLongClickListener);
            btn3.setOnLongClickListener(onLongClickListener);
            btn4.setOnLongClickListener(onLongClickListener);
        }
        if (Singleton.getInstancia(getActivity()).etapas == 4) {
            target1 = (LinearLayout) view.findViewById(R.id.target1);

            test1 = (Button) view.findViewById(R.id.test1);

            btn1 = (Button) view.findViewById(R.id.btn2);
            btn2 = (Button) view.findViewById(R.id.btn1);
            btn3 = (Button) view.findViewById(R.id.btn3);
            btn4 = (Button) view.findViewById(R.id.btn4);

            target1.setOnDragListener(dragListener);

            btn1.setOnLongClickListener(onLongClickListener);
            btn2.setOnLongClickListener(onLongClickListener);
            btn3.setOnLongClickListener(onLongClickListener);
            btn4.setOnLongClickListener(onLongClickListener);

            btn1.setBackgroundColor(Color.RED);
            btn2.setText("2");
            btn1.setText("1");

        }
        if (Singleton.getInstancia(getActivity()).etapas == 6) {
            target1 = (LinearLayout) view.findViewById(R.id.target1);

            test1 = (Button) view.findViewById(R.id.test1);

            btn1 = (Button) view.findViewById(R.id.btn4);
            btn2 = (Button) view.findViewById(R.id.btn2);
            btn3 = (Button) view.findViewById(R.id.btn3);
            btn4 = (Button) view.findViewById(R.id.btn1);

            target1.setOnDragListener(dragListener);

            btn1.setOnLongClickListener(onLongClickListener);
            btn2.setOnLongClickListener(onLongClickListener);
            btn3.setOnLongClickListener(onLongClickListener);
            btn4.setOnLongClickListener(onLongClickListener);

            btn1.setBackgroundColor(Color.BLACK);
            btn1.setText("1");
            btn4.setText("4");

        }

        return view;
    }

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, dragShadowBuilder, v, 0);
            return true;
        }
    };
    View.OnDragListener dragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();


            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:

                    if (view == btn1 && v.getId() == R.id.target1) {
                        LinearLayout linearLayout = (LinearLayout) view.getParent();
                        linearLayout.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        test1.setVisibility(View.GONE);
                        newParent.addView(view);

                        if (Singleton.getInstancia(getActivity()).etapas == 2) {
                            Singleton.getInstancia(getActivity()).etapas = 3;
                        } else if (Singleton.getInstancia(getActivity()).etapas == 4) {
                            Singleton.getInstancia(getActivity()).etapas = 5;
                        } else if (Singleton.getInstancia(getActivity()).etapas == 6) {
                            Singleton.getInstancia(getActivity()).etapas = 7;
                        }


                    } else {

                        Snack("¡INCORRECTO!");
                    }

                    break;
            }

            return true;
        }
    };

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

    public void Snack(String mensaje) {
        getActivity().findViewById(android.R.id.content);
        Snackbar.make(getActivity().findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_LONG).show();
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
