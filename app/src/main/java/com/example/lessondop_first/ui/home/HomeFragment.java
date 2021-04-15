package com.example.lessondop_first.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lessondop_first.R;
import com.example.lessondop_first.databinding.FragmentHomeBinding;
import com.example.lessondop_first.ui.home.HomeAdapter.HomeAdapter;
import com.example.lessondop_first.ui.home.HomeAdapter.HomeModel;
import com.example.lessondop_first.ui.home.HomeAdapter.Listen;

public class HomeFragment extends Fragment implements Listen {

    private NavController navController;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        click();

        binding.rv.setAdapter(homeAdapter);
        getDataInForm();
        return binding.getRoot();

    }

    private void click(){
        binding.fabAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_home_to_formFragment);
        });
    }

    private void getDataInForm(){
        //добавление
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String a = result.getString("name");
                String b = result.getString("number");
                Log.e("TAG", "onFragmentResult: " + "name " + a);
                int id= result.getInt("id");

                HomeModel model = homeAdapter.getModelToID(id);
                if (model != null){
                    model.setTitle(a);
                    model.setDescription(b);
                }else{
                    homeAdapter.addModel(new HomeModel(a,b));
                }

            }
        });
    }

    @Override
    public void setDataForForm(HomeModel homeModel, int position) {
            Bundle bundle = new Bundle();
            bundle.putString("name1", homeModel.getTitle());
            bundle.putString("number1", homeModel.getDescription());
            bundle.putInt("id", homeModel.getId());
            getParentFragmentManager().setFragmentResult("2", bundle);
            navController.navigate(R.id.action_navigation_home_to_formFragment);
    }

    @Override
    public void del(int position) {
        AlertDialog.Builder adg = new AlertDialog.Builder(binding.getRoot().getContext());
        String positive = "Да";
        String negative = "Нет";
        adg.setMessage("Вы хотите удалить ?");
        adg.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                homeAdapter.del(position);

            }
        });
        adg.setNegativeButton(negative, null);
        adg.show();
    }
}