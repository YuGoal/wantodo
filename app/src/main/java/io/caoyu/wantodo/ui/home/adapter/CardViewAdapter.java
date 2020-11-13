package io.caoyu.wantodo.ui.home.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * user caoyu
 * date 2020/11/4
 * time 14:14
 */
public class CardViewAdapter extends FragmentPagerAdapter {

    private final FragmentManager fragmentManager;
    private final List<Fragment> mFragmentList;
    private final String[] titles = {"文章","问答","体系"};
    public CardViewAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentManager = fm;
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = mFragmentList.get(position);
        fragmentManager.beginTransaction().hide(fragment).commit();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
