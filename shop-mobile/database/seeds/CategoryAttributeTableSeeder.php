<?php

use Illuminate\Database\Seeder;
use Faker\factory as Faker;
use App\ProductType;
class CategoryAttributeTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
    	$categories = [];
        $faker = Faker::create();
        $productTypes = ProductType::all()->pluck('id')->toArray();
        $faker->addProvider(new CompanyNameGenerator\FakerProvider($faker));
        $RECORD_COUNT =10;
        $product_attributes = [];

        for ($category_id = 1; $category_id <= $RECORD_COUNT; $category_id ++) {
        	$categories[] = [
        		'id' 				=> $category_id,
        		'category_name'		=> $faker->companyName,
        	];
        	for ($i = 0; $i < count($productTypes); $i++) {
        		if ($faker->numberBetween($min = 1, $max = 10) > 7) {
        			$product_attributes[] = [
        				'product_type_id'	=> $productTypes[$i],
        				'category_attribute_id'	=> $category_id
        			];
        		}
        	}
        }

        foreach (array_chunk($product_attributes, 500) as $data) {
        	DB::table('product_attributes')->insert($data);
        }

        foreach (array_chunk($categories, 500) as $data) {
        	DB::table('category_attributes')->insert($data);
        }
    }
}
