<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use Carbon\Carbon;
class EmployeeTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $employments = [];
        $faker = Faker::create();
        $RECORD_COUNT =30;

        for ($employment_id = 1; $employment_id <= $RECORD_COUNT; $employment_id ++) {
        	$employments[] = [
        		'id' => $employment_id,
        		'name' => $faker->name,
        		'phone_number' => $faker->phoneNumber,
        		'address' => $faker->address,
        		'type'	=> '1',
        		'created_at' => Carbon::now()
        	];
        }

        foreach (array_chunk($employments, 500) as $data) {
        	DB::table('employees')->insert($data);
        }
    }
}
