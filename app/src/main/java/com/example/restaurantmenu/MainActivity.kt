package com.example.restaurantmenu

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfFoods = ArrayList<Food>()
    var myFoodAdapter:FoodAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load food
        listOfFoods.add(Food("Coffee", getString(R.string.coffee), R.mipmap.coffee_pot))
        listOfFoods.add(Food("Espresso", "This is cooffee", R.mipmap.espresso))
        listOfFoods.add(Food("Honey", "This is cooffee", R.mipmap.honey))
        listOfFoods.add(Food("FrenchFries", "This is cooffee", R.mipmap.french_fries))
        listOfFoods.add(Food("IceCream", "This is cooffee", R.mipmap.strawberry_ice_cream))
        listOfFoods.add(Food("SugarCubes", "", R.mipmap.sugar_cubes))

        myFoodAdapter = FoodAdapter(this, listOfFoods)

        gvListFood.adapter = myFoodAdapter
    }

    class FoodAdapter: BaseAdapter {
        var listOfFood = ArrayList<Food>()
        var context: Context?=null
        constructor(context:Context, listOfFood:ArrayList<Food>):super(){
            this.context = context
            //enable listoffood for using here
            this.listOfFood = listOfFood
        }
        override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
            //get info about selected food
            val food = this.listOfFood[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //second parameter doesn't matter
            var foodView = inflator.inflate(R.layout.food_ticket, null)
            //don't forget about null-safety in Food.kt
            foodView.ivFoodImage.setImageResource(food.image!!)

            foodView.ivFoodImage.setOnClickListener {
                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name", food.name!!)
                intent.putExtra("des", food.des!!)
                intent.putExtra("image", food.image!!)

                context!!.startActivity(intent)
            }

            foodView.tvFoodName.text = food.name!!

            return foodView
        }

        override fun getItem(p0: Int): Any {
            return listOfFood[p0]

        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()

        }

        override fun getCount(): Int {
            return listOfFood.size

        }


    }
}
