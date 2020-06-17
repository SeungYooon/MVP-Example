package com.example.rxjava.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjava.R
import com.example.rxjava.base.BaseActivity
import com.example.rxjava.model.Dog
import com.example.rxjava.presenter.SearchContract
import com.example.rxjava.presenter.SearchPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), SearchContract.View {

    // MainActivity와 1:1 대응하는 SearchPresenter를 연결시켜주기 위한 초기화 지연 변수
    private lateinit var searchPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SearchContract.View를 상속받는 Activity가 생성이 되었다는 것을 Presenter에게 전달
        searchPresenter.takeView(this)

        getDogList()
    }

    // 버튼 클릭시 Presenter에 이벤트 발생함을 전달과 동시에 Model로부터 데이터 가져오라는 것을 알려줌
    private fun getDogList() {
        getDogListButton.setOnClickListener {
            searchPresenter.getDogList()
        }
    }

    // Model로 부터 받은 데이터를 Presenter에서 View로 전달, TextView에 보여줌
    @SuppressLint("SetTextI18n")
    override fun showDogList(dogList: List<Dog>) {
        firstDogText.text = "강아지 이름:${dogList[0].name}, 나이:${dogList[0].age}"
        secondDogText.text = "강아지 이름:${dogList[1].name}, 나이:${dogList[1].age}"
        thirdDogText.text = "강아지 이름:${dogList[2].name}, 나이:${dogList[2].age}"
    }

    override fun onDestroy() {
        super.onDestroy()
        searchPresenter.dropView()
    }

    // BaseActivity에서 Activity가 생성이되면 해당 Activity에 Presenter를 초기화
    override fun initPresenter() {
        searchPresenter = SearchPresenter()
    }

    override fun showError(error: String) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        searchRefresh.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        searchRefresh.visibility = View.GONE
    }
}