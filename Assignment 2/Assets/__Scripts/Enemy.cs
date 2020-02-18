using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [Header("Set in Inspector: Enemy")]
    public float speed = 10f;//the enemy moving speed

    
    
    // Start is called before the first frame update
    public Vector3 pos
	{
		get
		{
            return (this.transform.position);
		}
		set
		{
            this.transform.position = value;
		}
	}
    private BoundsCheck _bndCheck;//a
    void Start()//implementing boundCheck for the enemies to be destroyed after going out of bounds
	{
        _bndCheck = GetComponent<BoundsCheck>();
        
    }
    // Update is called once per frame
    void Update()//implementing boundCheck for the enemies to be destroyed after going out of bounds
    {
        
        Move();
        if (_bndCheck != null && _bndCheck.offDown)//c
		{
            
                Destroy(gameObject);
			
		}
    }

    public virtual void Move()//the movement of Enemy_1
	{
        
            Vector3 tempPos = pos;
            tempPos.y -= speed * Time.deltaTime;
            pos = tempPos;
        
       
	}

    void OnCollisionEnter(Collision coll)//the collision detector between the enemy and the bullet
	{
        GameObject otherGO = coll.gameObject;
        if (otherGO.tag == "ProjectileHero")
		{
            Destroy(otherGO);
            Destroy(gameObject);
		}
		else
		{
            print("Enemy hit by non-projectileHero" + otherGO.name);
		}
	}
}
