<?php

use Illuminate\Database\Seeder;
use Carbon\Carbon;
use Faker\Factory as Faker;
class CustomerTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $customers = [];
        $faker = Faker::create();
        $RECORD_COUNT =400;

        for ($customer_id = 1; $customer_id <= $RECORD_COUNT; $customer_id ++) {
        	$customers[] = [
        		'id' => $customer_id,
        		'name' => $faker->name,
        		'phone_number' => $faker->phoneNumber,
                'address'       => $faker->address,
        		'email'	=> $faker->email,
        		'created_at' => Carbon::now()
        	];
        }

        foreach (array_chunk($customers, 500) as $data) {
        	DB::table('customers')->insert($data);
        }
    }
}
