package com.aristys.aristysapp.ui.ux.describe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.UX;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class UXFragment extends Fragment {

  private RecyclerView UXRecyclerView;
  private UXViewAdapter UXAdapter;
  public List<UX> uxList;

  public static UXFragment newInstance() {
    return new UXFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_ux, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    uxList = new ArrayList<>();

    UXRecyclerView = (RecyclerView) getView().findViewById(R.id.ux_recyclerView);
    UXRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    UXRecyclerView.setHasFixedSize(true);
    UXRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    UXAdapter = new UXViewAdapter(getContext(), uxList);
    UXRecyclerView.setAdapter(UXAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.ux_experience,
      R.drawable.ux_usability,
      R.drawable.ux_personalization,
      R.drawable.ux_conversion,
      R.drawable.ux_interactivity,
      R.drawable.ux_readability,
      R.drawable.ux_contents,
      R.drawable.ux_aida,
      R.drawable.ux_immediate,

    };

    UX u = new UX("User Experience","L’alliance de l'ergonomie et du web-design au service de votre projet", "Tous nos sites, selon vos besoins (Chatbot, animations...), suivront les dernières recommandationsen terme d'UX (expérience utilisateur) et d'UI (design du site). Demandez aussi votre analyse UX / UI.", covers[0]);
    uxList.add(u);

    u = new UX("Usabilité","Facilitez la navigation","Ce terme désigne la capacité d'un utilisateur à utiliser un produit, à comprendre l'usage d'un produit : à quoi il sert, et comment on s'en sert. C'est un axe fondamental qui doit être intégré dans toute démarche d'UX design. Grâce à nous, votre site sera desormais limpide !", covers[1]);
    uxList.add(u);

    u = new UX("Personnalisation","Faire une première bonne impression","La personnalisation consiste à cibler les besoins des clients et ainsi leur proposer une offre qui leur correspond durant la navigation. Elle permet d’augmenter leur satisfaction car le client se sent privilégié. Elle est ainsi indispensable pour construire une relation.", covers[2]);
    uxList.add(u);

    u = new UX("Conversion","Gagnez des clients","En moyenne, 75% des internautes ne restent que 2 minutes sur un site internet s’ils n’ont pas trouvé ce qu’ils cherchaient. Par conséquent, les internautes ne sont pas seulement devenus exigeants ils sont également devenus impatients. D’où la nécessité pour votre site d’être facile à utiliser et de les aider à trouver ce qu’ils recherchent de manière simple et efficace.", covers[3]);
    uxList.add(u);

    u = new UX("Interactivité","Votre visiteur devient acteur !","L’expérience utilisateur désigne la démarche qui consite à influencer la perception qui résulte chez une personne de son interaction avec un produit, un service ou un environnement. C’est la dimension émotionnelle qui consitue le cœur de notre démarche UX.", covers[4]);
    uxList.add(u);

    u = new UX("Lisibilité","Favorisez une bonne lecture","Un article qui est illisible à cause des contrastes de couleurs peut entrainer une très mauvaise lecture. L’argument premier de faire un site est bien pour son contenu de base. Si celui-ci n’est pas lisible toute l’utilité et l’importance d’un site perd de son intérêt.", covers[5]);
    uxList.add(u);

    u = new UX("Contenus","Mettez en avant vos produits","Les moteurs de recherche mettent en avant les sites apportant une vraie réponse à la requête formulée par l’internaute. Cette réponse ne peut être apporté que par un contenu unique, de qualité et informatif. Même dans le cas du e-commerce, plus un produit sera bien décrit, plus le site a de chance de répondre à la demande de l’internaute.", covers[6]);
    uxList.add(u);

    u = new UX("AIDA","Attention/Intérêt/Désir/Action","L’analyse AIDA est un bon complément à la compréhension des problèmes d’un site aux tests utilisateurs. Elle permet, en se posant les bonnes questions, de détecter les points faibles d’une page. C’est une méthode tirée de la vente directe et qui permet de décomposer en quatre étapes les actions d’un utilisateur par rapport à une page.", covers[7]);
    uxList.add(u);

    u = new UX("Immédiat","Moins de 15sec pour charger une page","La vitesse d’affichage impacte doublement votre réussite sur le web, une page rapide à charger plaira aux utilisateur mais aussi au moteur de recherche ! Alors faites nous confiance pour améliorer la vitesse de chargement de vos pages.", covers[8]);
    uxList.add(u);

    UXAdapter.notifyDataSetChanged();
  }
}

