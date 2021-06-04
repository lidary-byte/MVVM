package cn.ondu.basecommon.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */

class BaseFragmentPagerAdapter(
    fm: FragmentManager,
    private val fragments: MutableList<Fragment>,
    private val title: MutableList<String>,
    private val behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = title[position]

}