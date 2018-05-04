<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
class ProviderTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $producers = [];
        $faker = Faker::create();
        $faker->addProvider(new CompanyNameGenerator\FakerProvider($faker));
        $RECORD_COUNT =10;

        for ($producer_id = 1; $producer_id <= $RECORD_COUNT; $producer_id ++) {
        	$producers[] = [
        		'id' => $producer_id,
        		'name' => $faker->companyName,
        	];
        }

        foreach (array_chunk($producers, 500) as $data) {
        	DB::table('providers')->insert($data);
        }
    }
}
