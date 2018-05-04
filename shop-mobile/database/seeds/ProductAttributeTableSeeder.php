<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use App\ProductType;
class ProductAttributeTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $productAttributes = [];
        $product_attribute = [];
        $productTypes = ProductType::getAll()->pluck('id')->toArray();
        $faker = Faker::create();
        $RECORD_COUNT = 10;
        $attributes = ['Hang San Xuat', 'He Dieu Hanh','Muc Gia','Ram','Loai CPU','Rom','Thuong Hieu'];
        for ($productAttribute_id = 1; $productAttribute_id <= count($attributes); $productAttribute_id ++) {
        	$productAttributes[] = [
        		'id' => $productAttribute_id,
        		'category_name' => $types[$productAttribute_id-1],
        	];

        	for($type_id = 0; $type_id < count($productTypes); $type_id ++) {
        		$product_attribute[] = [
        			'product_type_id'	=> $type_id,
        			'attribute_id'		=> $productAttribute_id
        		];
        	}

        }


        foreach (array_chunk($productAttributes, 500) as $data) {
        	DB::table('product_attributes')->insert($data);
        }

        foreach (array_chunk($product_attribute, 500) as $data) {
        	DB::table('attribute_product')->insert($data);
        }
    }
}
