using System.Collections;
using System.Collections.Generic;
using UnityEngine;


/// <summary>
/// this is the hero class
/// </summary>
///

public class Hero : MonoBehaviour
{
    // Start is called before the first frame update
    static public Hero S;//the static reference of the class to get the shieldLevel value
    [Header("Set in Inspector")]
    
    public float speed = 30;
    public float rollMult = -45;//represents how fast the ship rotates left and right
    public float pitchMult = 30;//represents how fast the ship rotates front and back
    public float gameRestartDelay = 2f;//the delay in seconds for the how long the scenes should wait to reload
    public GameObject projectilePrefab;//the reference of the prefab that being used as projectile in this case
    public float projectileSpeed = 40;//the speed used in the projectile's rigidbody

    [Header("Set Dynamically")]
    [SerializeField]
    private float _shieldLevel = 1;//the initial shield level
    private GameObject lastTriggerGo = null;//the gurdian object that checks if the same thing is being triggered twice
    // Update is called once per frame

    void Start()//the start method
	{
        if (S == null)
		{
            S = this;
		}else {
            Debug.LogError("Hero.Awake() - attempted to assign second Hero.S");
        }
	}//a
    void Update()//updated every frame and in charge of user interactions such as shooting and moving
    {
        float xAxis = Input.GetAxis("Horizontal");//b
        float yAxis = Input.GetAxis("Vertical");//b
        Vector3 pos = transform.position;

        pos.x += xAxis * speed * Time.deltaTime;
        pos.y += yAxis * speed * Time.deltaTime;
        transform.position = pos;

        transform.rotation = Quaternion.Euler(yAxis * pitchMult, xAxis * rollMult, 0);//c

        if (Input.GetKeyDown(KeyCode.Space))
        {
            TempFire();
        }
    }
    
    void OnTriggerEnter (Collider other)//the method that checks if the projectile is collided with the enemy
	{
        Transform rootT = other.gameObject.transform.root;
        GameObject go = rootT.gameObject;
        if (go == lastTriggerGo)
		{
            return;
		}
        lastTriggerGo = go;
        if (go.tag == "Enemy")
		{
            shieldLevel--;
            Destroy(go);
		}
		else
		{
            print("Triggered by non-enemy" + go.name);
		}
	}
    public float shieldLevel//property of the variable shieldLevel
	{
		get
		{
            return (_shieldLevel);
		}
		set
		{
            _shieldLevel = Mathf.Min(value, 4);
            if (value < 0)
			{
                Destroy(this.gameObject);
                Main.S.DelayedRestart(gameRestartDelay);
			}
		}
	}
    void TempFire()//the method in charge of setting up the clone and shooting the projectile prefab
	{
        GameObject ProjGO = Instantiate<GameObject>(projectilePrefab);
        ProjGO.transform.position = transform.position;
        Rigidbody rigidB = ProjGO.GetComponent<Rigidbody>();
        rigidB.velocity = Vector3.up * projectileSpeed;
    }
}
