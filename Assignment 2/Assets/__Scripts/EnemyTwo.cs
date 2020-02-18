using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyTwo : Enemy
{
    [Header("Set in Inspector: Enemy_2")]

    [HideInInspector]
    public int rand = 1;//the rand variable that allows the movement to be switched in between
    private BoundsCheck _bndCheck2;

    void Start()//b
    {
        _bndCheck2 = GetComponent<BoundsCheck>();
        InvokeRepeating("MoveRand", 0, 2f);
    }

    public override void Move()//special movement for Enemy_2: Enemy_2 should move on a 45° line randomly choosing left or right. 
    {

        if (gameObject.name == "Enemy_2(Clone)")
        {
            if (_bndCheck2.offLeft || _bndCheck2.offRight)
            {
                rand = -rand;
            }
            Vector3 tempPos = pos;
            tempPos.x += rand * speed * Time.deltaTime;
            tempPos.y -= speed * Time.deltaTime;
            pos = tempPos;
        }
    }
    void MoveRand()//used a random number generator to switch the integer from 0 to 1 which is later converted into 1 and -1 as adapting to the x axis movements
    {

        int random = Random.Range(0, 2);
        if (random == 0)
        {
            rand = -1;
        }
        else if (random == 1)
        {
            rand = 1;
        }
    }
}
