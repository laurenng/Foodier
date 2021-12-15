package edu.uw.foodier
// This file is for the homePage created by Lauren Ng
// and describes the high level functionality of the home page
// here, we create the card view and implement the main functionalities
// also creating the navigation to the bookmark activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.facebook.drawee.backends.pipeline.Fresco
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import edu.uw.foodier.databinding.HomePageBinding
import edu.uw.foodier.viewmodels.homePageViewModel
import kotlinx.android.synthetic.main.home_page.*

class homePage : Fragment(), CardStackListener {
    private var _binding: HomePageBinding? = null
    private val binding get() = _binding!!
    private val homePageModel : homePageViewModel by activityViewModels()
    private lateinit var adapter : FoodListAdapter
    private lateinit var layoutManager: CardStackLayoutManager
    private lateinit var dataSet : List<FoodItem>
    private var swipedDirection = "right"

    // when the view is created, I will be binding the current home page to the home activity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    // when the view is created, we will add the card view logic and populate the page to have
    // card to swipe, like tinder
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FoodListAdapter(homePageModel)
        createCardView();
    }

    // passes data to the adapter to display the cards, creates the layout for how the card
    // will be displayed and creates the scaffolding for actions and animations
    private fun createCardView() {
        dataSet = homePageModel.getFoodItems()
        adapter.updateData(dataSet);


        layoutManager = CardStackLayoutManager(context, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        stack_view.layoutManager = layoutManager
        stack_view.adapter = adapter
        stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        homePageModel.observeFoodItemUpdate().observe(viewLifecycleOwner, Observer {
            adapter.updateDistance(it)
            Log.d("HOMEPAGE","updating here!!")
        })
    }

    // when the card is disappeared, we will either add to the room database, if the user swiped
    // right or do nothing if they swiped left
    override fun onCardDisappeared(view: View?, position: Int) {
        if (swipedDirection == "right") {
            Log.d("HOMEPAGE", "The number is $position and values include ${dataSet[position].food_name}")
            // PASS TO ROOM HERE!!!!
            val likedFoodObject = dataSet[position]
        }
    }

    // identifies which direction the card was swiped
    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Left) {  // dislike
            swipedDirection = "left"
        } else if (direction == Direction.Right){  // like
            swipedDirection = "right"
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
//        TODO("Not yet implemented")
    }

    override fun onCardRewound() {
//        TODO("Not yet implemented")
    }

    override fun onCardCanceled() {
//        TODO("Not yet implemented")
    }

    override fun onCardAppeared(view: View?, position: Int) {
//        TODO("Not yet implemented")
    }
}