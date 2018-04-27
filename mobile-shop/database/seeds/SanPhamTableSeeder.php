<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use Carbon\Carbon;
use App\TradeMark;
use App\LoaiSanPham;
use App\NhaSanXuat;
class SanPhamTableSeeder extends Seeder
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
        $RECORD_COUNT =500;
        $trademarks = TradeMark::all()->pluck('id')->toArray();
        $productTypes = LoaiSanPham::all()->pluck('id')->toArray();
        $producers = NhaSanXuat::all()->pluck('id')->toArray();
        $product_trademark = [];
        for ($product_id = 1; $product_id <= $RECORD_COUNT; $product_id ++) {
        	$products[] = [
        		'id' 				=> 	$product_id,
        		'ten_sp' 			=> 	$faker->name,
        		'don_vi_tinh' 		=> 	'$',
        		'gia_chuan' 		=> 	$faker->numberBetween(10,2000),
        		'nha_sx'			=> 	$faker->randomElement($producers),
        		'mo_ta'				=>  $faker->text($maxNbChars = 500),
        		'so_luong'			=> 	$faker->numberBetween(15,300),
        		'id_loai_sp' 		=> 	$faker->randomElement($productTypes),
        		'thuong_hieu_sp'	=>	$faker->randomElement($trademarks),
        		'created_at' 		=> Carbon::now()
        	];
        }


        foreach (array_chunk($products, 500) as $data) {
        	DB::table('san_phams')->insert($data);
        }
    }
}
