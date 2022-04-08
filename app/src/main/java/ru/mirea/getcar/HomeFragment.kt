package ru.mirea.getcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import ru.mirea.getcar.R
import ru.mirea.getcar.onboarding.screens.FirstScreen
import ru.mirea.getcar.onboarding.screens.SecondScreen


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val bnvMain: MeowBottomNavigation = view.findViewById(R.id.bottomNavigation)

        bnvMain.add(MeowBottomNavigation.Model(1, R.drawable.shopping_bag))
        bnvMain.add(MeowBottomNavigation.Model(2, R.drawable.location))
        bnvMain.add(MeowBottomNavigation.Model(3, R.drawable.home))
        bnvMain.add(MeowBottomNavigation.Model(4, R.drawable.info_rect))
        bnvMain.add(MeowBottomNavigation.Model(5, R.drawable.user))

        bnvMain.show(3)

        bnvMain.setOnClickMenuListener {
            when(it.id){
                1 -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment,FirstScreen())
                        ?.commit()
                }
                2 -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment,SecondScreen())
                        ?.commit()
                }
            }
        }

        return view
    }
}