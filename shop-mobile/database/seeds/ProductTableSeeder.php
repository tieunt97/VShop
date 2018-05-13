<?php

use Illuminate\Database\Seeder;
use App\ProductType;
use App\Provider;
use Faker\Factory as Faker;
use Carbon\Carbon;
class ProductTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */

    public function run()
    {
        $products = [];
        $faker = Faker::create();
        $RECORD_COUNT =1000;
        $productTypes = ProductType::all()->pluck('id')->toArray();
        $producers = Provider::all()->pluck('id')->toArray();
        for ($product_id = 1; $product_id <= $RECORD_COUNT; $product_id ++) {
        	$products[] = [
        		'id' 				=> 	$product_id,
        		'product_name' 			=> 	$faker->name,
        		'unit' 		=> 	'$',
        		'base_price' 		=> 	$faker->numberBetween(10,2000),
        		'provider'			=> 	$faker->randomElement($producers),
        		'description'				=>  $faker->text($maxNbChars = 500),
        		'quantity'			=> 	$faker->numberBetween(15,300),
        		'product_type_id' 		=> 	$faker->randomElement($productTypes),
                'main_image'            =>  $faker->imageUrl($width = 640, $height = 480),
                'sub_images'            =>  json_encode($this->getImages(10))
        	];
        }


        foreach (array_chunk($products, 500) as $data) {
        	DB::table('products')->insert($data);
        }
    }

    function getImages($count) {
        $arr = [];
        $faker = Faker::create();
        for ($i = 0; $i < $count; $i++) {
            $arr [] = [$faker->imageUrl($width = 640, $height = 480)];
        }
        return $arr;
    }
}
