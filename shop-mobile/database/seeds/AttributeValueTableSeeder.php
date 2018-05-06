<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use App\Product;
class AttributeValueTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker::create();
        $products = Product::all()->pluck('id')->toArray();
        $productAttributes = DB::table('product_attributes')->pluck('id')->toArray();
        $RECORD_COUNT =10;
        $attribute_values = [];

        for ($product_id = 0; $product_id < count($products); $product_id ++) {
        		for($productAttribute_id = 0; $productAttribute_id < count($productAttributes); $productAttribute_id++) {
        			if ($faker->numberBetween($min = 1, $max = 10) > 6) {
	        			$attribute_values[] = [
	        				'attribute_product_id'	=> $productAttributes[$productAttribute_id],
	        				'product_id'			=> $products[$product_id],
	        				'value'					=> $faker->text($maxNbChars = 5)
	        			];
        			}
        		}

        }

        foreach (array_chunk($attribute_values, 500) as $data) {
        	DB::table('attribute_values')->insert($data);
        }
    }
}
