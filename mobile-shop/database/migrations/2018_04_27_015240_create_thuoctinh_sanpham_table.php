<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateThuoctinhSanphamTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('thuoctinh_sanpham', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('id_loai_sp')->unsigned();
            $table->integer('id_danh_muc_thuoc_tinh')->unsigned();
            $table->timestamps();

            $table->index('id_loai_sp');
            $table->index('id_danh_muc_thuoc_tinh');
            $table->unique(['id_loai_sp', 'id_danh_muc_thuoc_tinh']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('thuoctinh_sanpham');
    }
}
