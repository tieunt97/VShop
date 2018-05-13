<?php

use Illuminate\Database\Seeder;
use App\Product;
use Faker\Factory as Faker;
use App\User;
use Carbon\Carbon;
class EvaluationTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $evaluations = [];
        $faker = Faker::create();
        $RECORD_COUNT =500;
        $products = Product::all()->pluck('id')->toArray();
        $customers = User::select('id')->where('level', '=', 1)->pluck('id')->toArray();
        for ($product_id = 0; $product_id < count($products); $product_id ++) {
        	if(rand(1,20) > 19) {
        		for($i = 0; $i < count($customers); $i++) {
	        		if (rand(1,10) > 9) {
	        			$evaluations[] = [
		        			'product_id' => $product_id,
		        			'customer_id' => $i,
		        			'title'	=>	$faker->sentence($nbWords = 5, $variableNbWords = true),
		        			'content'	=> $faker->text($maxNbChars = 200),
		        			'star_number' => $faker->numberBetween($min = 1, $max = 5),
		        			'created_at'		=>Carbon::now()
	        			];
	        		}
        	 	}	
        	}
        }


        foreach (array_chunk($evaluations, 500) as $data) {
        	DB::table('evaluations')->insert($data);
        }
    }
}
