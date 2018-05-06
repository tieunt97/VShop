<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // $this->call(UsersTableSeeder::class);
        $this->call(EmployeeTableSeeder::class);
        $this->call(ProductTypeTableSeeder::class);
        $this->call(ProviderTableSeeder::class);
        $this->call(CustomerTableSeeder::class);
        $this->call(ProductTableSeeder::class);
        $this->call(CategoryAttributeTableSeeder::class);
        $this->call(AttributeValueTableSeeder::class);
    }
}
