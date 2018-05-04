<?php

namespace App;

use Eloquent as Model;
use App\Http\Services\MasterdataService;

class VersionedModel extends Model
{

    public static function getAll()
    {
        return MasterdataService::getOneTable(self::getTableName());
    }

    public static function findOneById($id)
    {
        return self::findWhere('id', $id)->first();
    }

    public static function findOne($key, $value)
    {
        return self::findWhere($key, $value)->first();
    }

    public static function findWhere($key, $value)
    {
        $records = self::getAll();
        return $records->where($key, $value);
    }

    public static function findWhereIn($key, Array $value)
    {
        $records = self::getAll();
        return $records->whereIn($key, array_unique($value));
    }
}