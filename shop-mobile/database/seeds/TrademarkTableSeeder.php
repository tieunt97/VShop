<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
class TrademarkTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $trademarks = [];
        $faker = Faker::create();
        $faker->addProvider(new CompanyNameGenerator\FakerProvider($faker));
        $RECORD_COUNT =10;

        for ($trademark_id = 1; $trademark_id <= $RECORD_COUNT; $trademark_id ++) {
        	$trademarks[] = [
        		'id' => $trademark_id,
        		'name' => $faker->companyName,
        	];
        }

        foreach (array_chunk($trademarks, 500) as $data) {
        	DB::table('trademarks')->insert($data);
        }
    }
}
