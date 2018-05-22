<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
class InputBillTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $inputBills = [];
        $faker = Faker::create();
        $RECORD_COUNT =1000;
        for ($product_id = 1; $product_id <= $RECORD_COUNT; $product_id ++) {
        	$inputBills[] = [
        		'id' 				=> 	$product_id,
        		'product_name' 			=> 	$faker->name,
        		'unit' 		=> 	'$',
        		'base_price' 		=> 	$faker->numberBetween(10,2000),
        		'provider'			=> 	$faker->randomElement($producers),
        		'description'				=>  $faker->text($maxNbChars = 500),
        		'quantity'			=> 	$faker->numberBetween(15,300),
        		'product_type_id' 		=> 	$faker->randomElement($productTypes),
                'main_image'            =>  $faker->imageUrl($width = 640, $height = 480),
                'sub_images'            =>  json_encode($this->getImages(10)),
                'created_at'            =>  $faker->dateTimeBetween($startDate = '-2 years', $endDate = 'now', $timezone = null)
        	];
        }


        foreach (array_chunk($inputBills, 500) as $data) {
        	DB::table('input_bills')->insert($data);
        }
    }
}
