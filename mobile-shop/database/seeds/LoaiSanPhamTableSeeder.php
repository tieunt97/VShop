<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
class LoaiSanPhamTableSeeder extends Seeder
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
        $RECORD_COUNT =15;

        for ($productType_id = 1; $productType_id <= $RECORD_COUNT; $productType_id ++) {
        	$productTypes[] = [
        		'id' => $productType_id,
        		'ten_loai_sp' => $faker->word,
        	];
        }

        foreach (array_chunk($productTypes, 500) as $data) {
        	DB::table('loai_san_phams')->insert($data);
        }
    }
}
