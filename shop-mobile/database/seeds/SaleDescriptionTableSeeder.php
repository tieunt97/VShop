<?php

use Illuminate\Database\Seeder;
use App\Product;
use App\SaleBill;
use Faker\Factory as Faker;
use Carbon\Carbon;
class SaleDescriptionTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $saleDescriptions = [];
        $faker = Faker::create();
        $products = Product::select('id')->pluck('id')->toArray();
        $saleBills = SaleBill::select('id')->pluck('id')->toArray();

        for ($sale_description_id = 1; $sale_description_id < count($saleBills); $sale_description_id ++) {
        	for ($i = 0; $i < count($products); $i++) {
        		if (rand(1,1000) > 990) {
        			$saleDescriptions[] = [
		        		'sale_bill_id' => $sale_description_id,
		        		'product_id'	=> $products[$i],
		        		'amount' => rand(1,10),
		        		'created_at' => Carbon::now()
        			];
        		}
        	}
        	
        }

        foreach (array_chunk($saleDescriptions, 500) as $data) {
        	DB::table('sale_descriptions')->insert($data);
        }
    }	
}
