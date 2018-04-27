<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use Carbon\Carbon;
class KhachHangTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //
        $customers = [];
        $faker = Faker::create();
        $RECORD_COUNT =30;

        for ($customer_id = 1; $customer_id <= $RECORD_COUNT; $customer_id ++) {
        	$customers[] = [
        		'id' => $customer_id,
        		'ho_ten' => $faker->name,
        		'so_dt' => $faker->phoneNumber,
        		'email'	=> $faker->email,
        		'created_at' => Carbon::now()
        	];
        }

        foreach (array_chunk($customers, 500) as $data) {
        	DB::table('khach_hangs')->insert($data);
        }
    }
}
