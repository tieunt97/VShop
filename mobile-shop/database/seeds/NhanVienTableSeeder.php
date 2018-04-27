<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use Carbon\Carbon;
class NhanVienTableSeeder extends Seeder
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
        		'ten_nv' => $faker->name,
        		'so_dt' => $faker->phoneNumber,
        		'dia_chi' => $faker->address,
        		'loai_nv'	=> '1',
        		'created_at' => Carbon::now()
        	];
        }

        foreach (array_chunk($employments, 500) as $data) {
        	DB::table('nhan_viens')->insert($data);
        }
    }
}
