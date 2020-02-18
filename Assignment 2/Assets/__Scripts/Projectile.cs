using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Projectile : MonoBehaviour
{

    private BoundsCheck _bndCheck;//implementing a bounds check object to help deleting the gameObject when exited the screen
    // Start is called before the first frame update
    void Start()
    {
        _bndCheck = GetComponent<BoundsCheck>();
    }

    // Update is called once per frame
    void Update()
    {
        if (_bndCheck.offUp)//if the bullet is over the top of the canvas, it would be deleted
		{
            Destroy(gameObject);
		}
    }
}
