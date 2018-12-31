package fr.everydaysapps.marvelsuperheroes.screens.heroes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;

import javax.inject.Inject;

import fr.everydaysapps.marvelsuperheroes.application.AppController;
import fr.everydaysapps.marvelsuperheroes.models.Hero;
import fr.everydaysapps.marvelsuperheroes.screens.details.HeroDetailsActivity;
import fr.everydaysapps.marvelsuperheroes.screens.heroes.core.HeroesPresenter;
import fr.everydaysapps.marvelsuperheroes.screens.heroes.core.HeroesView;
import fr.everydaysapps.marvelsuperheroes.screens.heroes.dagger.DaggerHereosComponent;
import fr.everydaysapps.marvelsuperheroes.screens.heroes.dagger.HeroesModule;

/**
 * Created by yassinegharsallah on 01/04/2017.
 */

public class HeroesListActivity extends Fragment {


    @Inject
    HeroesView view;
    @Inject
    HeroesPresenter presenter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerHereosComponent.builder().appComponent(AppController.getNetComponent()).heroesModule(new HeroesModule(this)).build().inject(this);
        Toast.makeText(getActivity(), "blah!", Toast.LENGTH_SHORT).show();
//        getActivity().setContentView(view.view());
        presenter.onCreate();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view.view();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void goToHeroDetailsActivity(Hero hero) {

        Intent in = new Intent(getActivity(), HeroDetailsActivity.class);
        in.putExtra("hero", (Serializable) hero);
        startActivity(in);

    }

}
