using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class Main : MonoBehaviour
{
    static public Main S;
    [Header("Set in Inspector")]
    public GameObject[] prefabEnemies;
    public float enemySpawnPerSecond = 0.5f;
    public float enemyDefaultPadding = 1.5f;
    private BoundsCheck _bndCheck;
    // Start is called before the first frame update
    void Start()
    {
        S = this;
        _bndCheck = GetComponent<BoundsCheck>();//implementing boundCheck for the enemies to be destroyed after going out of bounds
        Invoke("SpawnEnemy", 1f / enemySpawnPerSecond);
    }
    public void SpawnEnemy()//the method that is in charge of the spawn of the enemy
	{
        int ndx = Random.Range(0, prefabEnemies.Length);
        GameObject go = Instantiate<GameObject>(prefabEnemies[ndx]);

        float enemyPadding = enemyDefaultPadding;
        if (go.GetComponent<BoundsCheck>() != null)
		{
            enemyPadding = Mathf.Abs(go.GetComponent<BoundsCheck>().radius);

		}

        Vector3 pos = Vector3.zero;
        float xMin = -_bndCheck.camWidth + enemyPadding;
        float xMax = _bndCheck.camWidth - enemyPadding;
        pos.x = Random.Range(xMin, xMax);
        pos.y = _bndCheck.camHeight + enemyPadding;
        go.transform.position = pos;

        Invoke("SpawnEnemy", 1f / enemySpawnPerSecond);
    }
    public void DelayedRestart(float delay)//when the game is over, the main scene is initialized after 2 seconds
	{
        Invoke("Restart", delay);
	}
    public void Restart()//the restart method that Invoke uses
	{
        SceneManager.LoadScene("_Scene_0");
	}
    
}
