<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateHoaDonBanHangsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hoa_don_ban_hangs', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('so_hd')->unsigned();
            $table->integer('id_kh')->unsigned();
            $table->integer('id_nv_giao_hang')->unsigned();
            $table->integer('so_thanh_toan')->unsigned();
            $table->integer('trang_thai')->nullable();
            $table->dateTime('ngay_giao_hang')->nullable();
            $table->dateTime('ngay_dat_hang');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('hoa_don_ban_hangs');
    }
}
