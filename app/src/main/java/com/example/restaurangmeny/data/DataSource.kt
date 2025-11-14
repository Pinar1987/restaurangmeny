package com.example.restaurangmeny.data

import com.example.restaurangmeny.R

object DataSource {
    val appetizers = listOf(
        Dish("Toast Skagen", "Klassisk Toast Skagen med räkor, majonnäs, dill och löjrom.", R.drawable.appetizers1, 125.0),
        Dish("Carpaccio", "Lövtunn oxfilé med olivolja, pinjenötter, ruccola och parmesan.", R.drawable.appetizers2, 135.0),
        Dish("Bruschetta", "Grillat bröd med tomat, vitlök, basilika och olivolja.", R.drawable.appetizers3, 110.0)
    )

    val mainCourses = listOf(
        Dish("Biff Rydberg", "Tärnad oxfilé med lök, potatis och äggula.", R.drawable.maindishes1, 255.0),
        Dish("Pestopasta", "Pasta med hemgjord pesto, soltorkade tomater och parmesan.", R.drawable.maindishes2, 195.0),
        Dish("Halstrad lax", "Lax med potatispuré, vitvinssås och sparris.", R.drawable.maindishes3, 235.0)
    )

    val desserts = listOf(
        Dish("Crème brûlée", "Klassisk fransk efterrätt med karamelliserat socker.", R.drawable.dessert1, 95.0),
        Dish("Chokladfondant", "Varm chokladkaka med rinnig mitt, serveras med vaniljglass.", R.drawable.dessert2, 105.0),
        Dish("Pannacotta", "Italiensk gräddpudding med smak av vanilj och toppad med bärsås.", R.drawable.dessert3, 85.0)
    )
}