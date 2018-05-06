<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
class ProductTypeTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $productTypes = [];
        $faker = Faker::create();
        $RECORD_COUNT =4;
        $types = ['Apple', 'Smartphone','Macbook','SmartWatch'];
        for ($productType_id = 1; $productType_id <= $RECORD_COUNT; $productType_id ++) {
        	$productTypes[] = [
        		'id' => $productType_id,
        		'product_type_name' => $types[$productType_id-1],
                'icon_image'    => $faker->imageUrl($width = 320, $height = 320),
        	];
        }

        foreach (array_chunk($productTypes, 500) as $data) {
        	DB::table('product_types')->insert($data);
        }
    }
}
