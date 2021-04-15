package com.example.lessondop_first.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lessondop_first.R;
import com.example.lessondop_first.databinding.FragmentFormBinding;

public class FormFragment extends Fragment {

    private NavController navController;
    FragmentFormBinding binding;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentFormBinding.inflate(inflater, container, false);
        SalamAleykum();
        getData();
        return binding.getRoot();


    }
    private void SalamAleykum(){
        binding.saveButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", binding.tittle.getText().toString());
            bundle.putString("number", binding.description.getText().toString());
            bundle.putInt("id", id);
            getParentFragmentManager().setFragmentResult("key", bundle);
            navController.navigateUp();

        });
    }

    public void getData(){
        getParentFragmentManager().setFragmentResultListener("2", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                binding.tittle.setText(result.getString("name1"));
                binding.description.setText(result.getString("number1"));
                id = result.getInt("id");
            }
        });
    }
}