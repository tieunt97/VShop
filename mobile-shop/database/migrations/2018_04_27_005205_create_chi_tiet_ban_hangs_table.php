<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateChiTietBanHangsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('chi_tiet_ban_hangs', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('so_hd')->unsigned();
            $table->integer('id_sp')->unsigned();
            $table->integer('don_gia')->unsigned();
            $table->integer('so_luong')->unsigned();
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
        Schema::dropIfExists('chi_tiet_ban_hangs');
    }
}
